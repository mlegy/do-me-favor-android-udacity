
package melegy.com.domeafavour.features.favors.favorsFeed;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import melegy.com.domeafavour.R;
import melegy.com.domeafavour.data.models.resources.Favor;

/**
 * Created by ahmad on 5/5/17.
 */

public class FavorsAdapter extends RecyclerView.Adapter<FavorsAdapter.ViewHolder> {

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
        SimpleDraweeView mImageViewAuthor;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    FavorsAdapter(List<Favor> favors) {
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
        if (favor.owner() != null) {
            holder.mTextViewAuthor.setText(favor.owner().firstName());
            holder.mImageViewAuthor.setImageURI(Uri.parse(favor.owner().avatar()));
        }
        holder.mTextViewDistance.setText(formatDistance(favor.distance(), holder));
    }

    private String formatDistance(float distance, ViewHolder holder) {
        if (distance <= 10)
            return holder.mTextViewDistance.getContext().getString(R.string.just_her);
        else if (distance < 1000)
            return holder.mTextViewDistance.getContext().getString(R.string.meters_away,
                    Math.round(distance));
        else
            return holder.mTextViewDistance.getContext().getString(R.string.km_away,
                    Math.round(Math.round(distance / 1000)));
    }

    @Override
    public int getItemCount() {
        return favors.size();
    }
}
