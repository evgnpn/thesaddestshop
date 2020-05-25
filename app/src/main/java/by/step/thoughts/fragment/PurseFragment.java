package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;
import java.util.UUID;

import by.step.thoughts.Constants;
import by.step.thoughts.MathExampleGenerator;
import by.step.thoughts.R;
import by.step.thoughts.entity.Purse;
import by.step.thoughts.viewmodel.DataViewModel;

import static by.step.thoughts.Constants.LOG_TAG;


public class PurseFragment extends Fragment {

    public static final String TAG = PurseFragment.class.getSimpleName() + " " + UUID.randomUUID().toString();

    private MathExampleGenerator mathGen = new MathExampleGenerator();
    private MathExampleGenerator.Result genRes;

    private Context context;
    private View view;
    private FragmentActivity activity;
    private TextInputLayout answerTil;
    private TextInputEditText answerTiet;
    private MaterialButton refreshMb;
    private MaterialButton sendMb;
    private TextView balanceTv;
    private TextView currencyTv;

    private DataViewModel dataViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onCreate (savedInstance: " + (savedInstanceState != null) + ")");

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onCreateView (savedInstance: " + (savedInstanceState != null) + ")");
        return inflater.inflate(R.layout.fragment_purse, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onActivityCreated (savedInstance: " + (savedInstanceState != null) + ")");

        initVars();
        generateExample();

        loadData();

        balanceTv.setText(Constants.CURRENCY);

        setListeners();

    }

    private void initVars() {
        context = requireContext();
        activity = requireActivity();
        view = requireView();
        balanceTv = view.findViewById(R.id.balance);
        currencyTv = view.findViewById(R.id.currency);
        answerTil = view.findViewById(R.id.answerTil);
        answerTil.setError(null);
        answerTil.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                answerTil.setError(null);
            }
        });

        answerTiet = view.findViewById(R.id.answerTiet);
        refreshMb = view.findViewById(R.id.refreshBtn);
        sendMb = view.findViewById(R.id.sendBtn);
        dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
    }

    private void setListeners() {
        refreshMb.setOnClickListener(v -> {
            generateExample();
        });
        sendMb.setOnClickListener(v -> {

            Editable text = answerTiet.getText();

            if (text != null) {
                int answer = Integer.parseInt(text.toString());
                if (genRes.getRes() == answer) {

                    LiveData<Purse> purseLiveData =
                            dataViewModel.getPurseRepository().getById("PRIMARY");

                    purseLiveData.observe(activity, new Observer<Purse>() {
                        @Override
                        public void onChanged(Purse purse) {
                            double randDouble = new Random().nextDouble() * 100;
                            purse.money += randDouble;
                            dataViewModel.getPurseRepository().update(new Purse[]{purse});

                            Snackbar.make(view, "+" + randDouble + " "
                                    + Constants.CURRENCY, Snackbar.LENGTH_SHORT)
                                    .setAnchorView(R.id.bottomNavBar).show();

                            generateExample();

                            purseLiveData.removeObserver(this);
                        }
                    });
                } else {
                    answerTil.setError("Не правильный ответ");
                }
            }
        });
    }

    private void loadData() {
        dataViewModel.getPurseRepository().getById(Constants.PURSE_ID).observe(activity, purse -> {
            balanceTv.setText(purse != null ? String.valueOf(purse.money) : "0");
        });
    }

    private void generateExample() {
        genRes = mathGen.next(1, 10);
        answerTil.setHint(genRes.getVal1() + " " + genRes.getOp() + " " + genRes.getVal2() + " =");
        answerTil.setError(null);
    }
}
