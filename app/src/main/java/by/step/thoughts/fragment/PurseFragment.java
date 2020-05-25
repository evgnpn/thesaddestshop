package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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

    public static final String TAG = UUID.randomUUID().toString();

    private MathExampleGenerator mathGen = new MathExampleGenerator();
    private MathExampleGenerator.Result genRes;

    private Context context;
    private View view;
    private FragmentActivity activity;
    private TextInputLayout answerTil;
    private TextInputEditText answerTiet;
    private MaterialButton refreshMb;
    private MaterialButton sendMb;


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
        return inflater.inflate(R.layout.fragment_purse, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "[" + this.getClass().getSimpleName() + "] onActivityCreated (savedInstance: " + (savedInstanceState != null) + ")");

        initVars();
        generateExample();

        dataViewModel.getPurseRepository().getById("PRIMARY").observe(activity, purse -> {

            if (purse != null) {
                Toast.makeText(context, "purse: " + purse.money, Toast.LENGTH_SHORT).show();
            }
        });

        setListeners();

    }

    private void initVars() {
        context = requireContext();
        activity = requireActivity();
        view = requireView();
        answerTil = view.findViewById(R.id.answerTil);
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

                    dataViewModel.getPurseRepository().getById("PRIMARY").observe(activity, purse -> {
                        double randDouble = new Random().nextDouble() * 10;
                        purse.money += randDouble;
                        Snackbar.make(view, "+" + randDouble + " " + Constants.CURRENCY, Snackbar.LENGTH_SHORT)
                                .setAnchorView(R.id.bottomNavBar).show();
                        dataViewModel.getPurseRepository().update(new Purse[]{purse});
                    });
                } else {
                    answerTil.setError("Не правильный ответ");
                }
            }

            // generateExample();
        });
    }

    private void generateExample() {
        genRes = mathGen.next(1, 10);
        answerTil.setHint(genRes.getVal1() + " " + genRes.getOp() + " " + genRes.getVal2() + " =");
    }
}
