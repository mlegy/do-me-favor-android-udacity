
package com.domefavor.android.features.favors.favorsFeed;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.domefavor.android.R;

/**
 * Created by ahmad on 5/8/17.
 */
public class FavorsAdapter extends CursorAdapter {

    static class ViewHolder {
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
            ButterKnife.bind(this, view);
        }
    }

    FavorsAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.favors_feed_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String title = cursor.getString(FeedActivityFragment.COL_FAVOR_TITLE);
        viewHolder.mTextViewTitle.setText(title);

        String desc = cursor.getString(FeedActivityFragment.COL_FAVOR_DESC);
        viewHolder.mTextViewDescription.setText(desc);

        Float dist = cursor.getFloat(FeedActivityFragment.COL_FAVOR_DISTANCE);
        viewHolder.mTextViewDistance.setText(formatDistance(dist, viewHolder));

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

}
