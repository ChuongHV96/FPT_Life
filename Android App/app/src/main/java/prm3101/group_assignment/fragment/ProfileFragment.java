package prm3101.group_assignment.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.LoginActivity;
import prm3101.group_assignment.model.Instructor;
import prm3101.group_assignment.model.Student;

public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Student> studentInfo;
    private List<Instructor> teacherInfo;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        getActivity().setTitle("Me");
    }

    public static ProfileFragment newInstance(List<Student> listStudent, List<Instructor> listIns) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("STUDENT_DETAIL", (Serializable) listStudent);
        bundle.putSerializable("INS_DETAIL", (Serializable) listIns);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        studentInfo = (List<Student>) getArguments().getSerializable("STUDENT_DETAIL");
        teacherInfo = (List<Instructor>) getArguments().getSerializable("INS_DETAIL");
        TextView username = (TextView) v.findViewById(R.id.username);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView code = (TextView) v.findViewById(R.id.code);
        TextView birth = (TextView) v.findViewById(R.id.birth);
        if (!studentInfo.isEmpty()){
            username.setText(studentInfo.get(0).getUsername());
            name.setText(studentInfo.get(0).getStuName());
            code.setText(studentInfo.get(0).getStuCode());
            birth.setText(studentInfo.get(0).getStuBirthday().substring(0, 10));
        } else {
            username.setText(teacherInfo.get(0).getUsername());
            name.setText(teacherInfo.get(0).getInsName());
            code.setText(teacherInfo.get(0).getInsCode());
            birth.setText(teacherInfo.get(0).getInsBirthDay().substring(0, 10));
        }
        RelativeLayout logout = (RelativeLayout) v.findViewById(R.id.logoutView);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
