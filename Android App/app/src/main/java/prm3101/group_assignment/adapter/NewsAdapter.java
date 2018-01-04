package prm3101.group_assignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.WebActivity;
import prm3101.group_assignment.model.Article;

/**
 * Created by ASUS on 24/10/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ArticleHolder> {

    private Activity activity;
    private ArrayList<Article> listAticle;
    private Context mContext;

    public NewsAdapter(Activity activity, ArrayList<Article> listAticle) {
        this.activity = activity;
        this.listAticle = listAticle;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_new, parent, false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(final ArticleHolder holder, final int position) {
        final Article article = listAticle.get(position);
        holder.title.setText(article.getTitle());
        holder.date.setText(article.getDate());
        Glide.with(activity)
                .load(article.getThumnail())
                .asBitmap()
                .centerCrop()
                .atMost()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .animate(android.R.anim.fade_in)
                .approximate()
                .into(holder.thumnail);
        Glide.with(activity)
                .load(article.getIcon())
                .asBitmap()
                .atMost()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .animate(android.R.anim.fade_in)
                .approximate()
                .into(holder.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to web view
                activity.startActivity(new Intent(activity, WebActivity.class).putExtra("ARTICLE", article.getUrl()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAticle.size();
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {

        private ImageView thumnail;
        private ImageView icon;
        private TextView title;
        private TextView date;

        public ArticleHolder(View itemView) {
            super(itemView);
            thumnail = (ImageView) itemView.findViewById(R.id.image);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
