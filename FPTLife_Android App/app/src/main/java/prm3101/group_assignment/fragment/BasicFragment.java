package prm3101.group_assignment.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.AddTaskActivity;
import prm3101.group_assignment.model.Schedule;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BasicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BasicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasicFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Schedule> todaySchedule;
    private int tomorrowSize, userId;

    private OnFragmentInteractionListener mListener;

    public BasicFragment() {
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

    public static BasicFragment newInstance(List<Schedule> list, int size, int userId) {
        BasicFragment fragment = new BasicFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("TODAY_SCHEDULE", (Serializable) list);
        bundle.putSerializable("TOMORROW_SIZE", (Serializable) size);
        bundle.putSerializable("USER_ID", (Serializable) userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    DayFragment dayFragment = new DayFragment();
    TasksFragment tasksFragment = new TasksFragment();

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.fragment_basic, null);
//        TabLayout tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        FloatingActionButton add = (FloatingActionButton) x.findViewById(R.id.addTask);

        final ViewPager viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(dayFragment);
        adapter.addFragment(tasksFragment);
        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.getTabAt(0).setCustomView(R.layout.tab_day_header_choose);
//        tabLayout.getTabAt(1).setCustomView(R.layout.tab_task_header);

        todaySchedule = (List<Schedule>) getArguments().getSerializable("TODAY_SCHEDULE");
        tomorrowSize = (int) getArguments().getSerializable("TOMORROW_SIZE");
        userId = (int) getArguments().getSerializable("USER_ID");
        dayFragment.newInstance((ArrayList<Schedule>) todaySchedule, tomorrowSize);
        Bundle bundle = new Bundle();
        bundle.putSerializable("TODAY_SCHEDULE_V2", (Serializable) todaySchedule);
        bundle.putSerializable("TOMORROW_SIZE_V2", (Serializable) tomorrowSize);
        dayFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.viewpager, dayFragment)
                .commit();
//        Log.e("BasicFragment", todaySchedule.toString());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), AddTaskActivity.class);
                i.putExtra("USER_ID", (Serializable) userId);
                startActivity(i);
            }
        });

        return x;
    }


    static class Adapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> mFragmentList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
//            return mFragmentList.size();
            return 1;
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

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
