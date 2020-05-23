package by.step.thoughts.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.UUID;

import by.step.thoughts.MathExampleGenerator;
import by.step.thoughts.R;
import by.step.thoughts.viewmodel.DataViewModel;

import static by.step.thoughts.Constants.LOG_TAG;


public class PurseFragment extends Fragment {

    public static final String TAG = UUID.randomUUID().toString();

    private MathExampleGenerator mathGen = new MathExampleGenerator();

    private Context context;
    private FragmentActivity activity;
    private TextInputLayout answerTil;
    private TextInputEditText answerTiet;

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

        MathExampleGenerator.Result genRes = mathGen.next(-20, 20);
        answerTil.setHint(genRes.getVal1() + " " + genRes.getOp() + " " + genRes.getVal2() + " =");


        dataViewModel.getPurseRepository().getById("PRIMARY").observe(activity, purse -> {

            Toast.makeText(context, "purse: " + purse.money, Toast.LENGTH_SHORT).show();
        });

    }

    private void initVars() {
        context = requireContext();
        activity = requireActivity();
        View view = requireView();
        answerTil = view.findViewById(R.id.answerTil);
        answerTiet = view.findViewById(R.id.answerTiet);
        dataViewModel = new ViewModelProvider(activity).get(DataViewModel.class);
    }
}
