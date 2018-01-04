package prm3101.group_assignment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import prm3101.group_assignment.R;
import prm3101.group_assignment.adapter.SubjectAdapter;
import prm3101.group_assignment.model.Schedule;
import prm3101.group_assignment.ulti.APIService;
import prm3101.group_assignment.ulti.AppUtils;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<Schedule> mSchedules;
    private APIService mService;
    private AppUtils utils = new AppUtils();
    private int mTomorrowSize;


    private OnFragmentInteractionListener mListener;

    public DayFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static DayFragment newInstance(ArrayList<Schedule> list, int size) {
        DayFragment fragment = new DayFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("TODAY_SCHEDULE_V2", (Serializable) list);
        bundle.putSerializable("TOMORROW_SIZE_V2", (Serializable) size);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day, container, false);
        //Get data
        mSchedules = (ArrayList<Schedule>) getArguments().getSerializable("TODAY_SCHEDULE_V2");
        mTomorrowSize = (int) getArguments().getSerializable("TOMORROW_SIZE_V2");

        TextView today = (TextView) v.findViewById(R.id.today);
        TextView tommorrow = (TextView) v.findViewById(R.id.tomorrow);
        TextView subjectInTomorrow = (TextView) v.findViewById(R.id.subjectsInTommorrow);
        today.setText("Today - " + utils.getDayFull());
        tommorrow.setText("Tomorrow - " + utils.getTommorrow());
        subjectInTomorrow.setText("" + mTomorrowSize + "\n Classes");
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.todaySchedule);

        //Adapter
        if (!mSchedules.isEmpty()) {
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            SubjectAdapter adapter = new SubjectAdapter(getActivity().getApplicationContext(), mSchedules);
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.setVisibility(View.GONE);
            today.setText("Today - " + utils.getDayFull() + "\n No Classes");
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
