package prm3101.group_assignment.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.WebActivity;
import prm3101.group_assignment.adapter.NewsAdapter;
import prm3101.group_assignment.model.Article;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static final String MY_URL = "http://fpt.edu.vn";
    private RecyclerView recycler;
    private NewsAdapter mNewsAdapter;
    private FragmentActivity mFragmentActivity;

    private OnFragmentInteractionListener mListener;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        // set news data
        recycler = (RecyclerView) v.findViewById(R.id.data);
        recycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
        new DownloadTask().execute(MY_URL);
        //Xem them
        TextView more = (TextView) v.findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getApplicationContext().startActivity
                        (new Intent(getActivity().getApplicationContext(), WebActivity.class)
                                .putExtra("MORE", "http://fpt.edu.vn/tin-moi-nhat"));
            }
        });
        return v;
    }



    private class DownloadTask extends AsyncTask<String, Void, ArrayList<Article>> {

        private static final String TAG = "DownloadTask";

        @Override
        protected ArrayList<Article> doInBackground(String... strings) {
            Document document = null;
            ArrayList<Article> listArticle = new ArrayList<>();
            try {
                document = (Document) Jsoup.connect(strings[0]).get();
                if (document != null) {
                    Elements sub = document.select("ul.main-list > li.media");
                    int count = 0;

                    for (Element element : sub) {
                        Article article = new Article();
                        if (sub.size() - 1 == count) {
                            // remove last element
                        } else {
                            Element img = element.getElementsByTag("a").first();
                            Element content = element.getElementsByClass("media-body").first();
//                        //Parse to model
                            if (content != null) {
                                String title = content.getElementsByTag("h4").first().text();
                                article.setTitle(title);
                                String url = content.getElementsByClass("fe-title-color").attr("href");
                                article.setUrl(MY_URL + url);
                                String date = content.getElementsByClass("row-alpha80").first().getElementsByTag("span").first().text();
                                article.setDate(date);
                                String icon = content.getElementsByClass("row-alpha80").first().getElementsByTag("img").attr("src");
                                article.setIcon(MY_URL + icon);
                            }
                            if (img != null) {
                                String src = img.getElementsByTag("img").attr("src");
                                article.setThumnail(MY_URL + src);
                            }
//                        //Add to list
                            listArticle.add(article);
                            count++;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return listArticle;
        }

        @Override
        protected void onPostExecute(ArrayList<Article> articles) {
            super.onPostExecute(articles);
            mNewsAdapter = new NewsAdapter(getActivity(), articles);
            recycler.setAdapter(mNewsAdapter);
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
