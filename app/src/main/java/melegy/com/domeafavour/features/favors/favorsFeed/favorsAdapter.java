
package melegy.com.domeafavour.features.favors.favorsFeed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import melegy.com.domeafavour.R;
import melegy.com.domeafavour.data.models.resources.Favor;

/**
 * Created by ahmad on 5/5/17.
 */

public class favorsAdapter extends RecyclerView.Adapter<favorsAdapter.ViewHolder> {

    private List<Favor> favors;

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_title)
        TextView mTextViewTitle;

        @BindView(R.id.text_description)
        TextView mTextViewDescription;

        @BindView(R.id.text_author)
        TextView mTextViewAuthor;

        @BindView(R.id.text_distance)
        TextView mTextViewDistance;

        @BindView(R.id.image_author)
        ImageView mImageViewAuthor;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public favorsAdapter(List<Favor> favors) {
        this.favors = favors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favors_feed_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Favor favor = favors.get(position);
        holder.mTextViewTitle.setText(favor.title());
        holder.mTextViewDescription.setText(favor.description());
        holder.mTextViewAuthor.setText(favor.owner().firstName());
        holder.mTextViewDistance.setText(favor.distance().toString());
    }

    @Override
    public int getItemCount() {
        return favors.size();
    }
}
