
package com.domefavor.android.features.favors.updateFavor;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.domefavor.android.App;
import com.domefavor.android.R;
import com.domefavor.android.data.models.resources.Favor;
import com.facebook.drawee.view.SimpleDraweeView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class FavorDetailsFragment extends Fragment {

    @Inject
    FavorVM favorVM;

    @BindView(R.id.text_favor_title)
    TextView favorTitle;

    @BindView(R.id.text_favor_details)
    TextView favorDetails;

    @BindView(R.id.image_author)
    SimpleDraweeView authorPhoto;

    @BindView(R.id.text_author)
    TextView authorName;

    @BindView(R.id.text_distance)
    TextView distanceText;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    private String favorId;
    private float distance;
    private String favorOwner;

    public FavorDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getApp().getVMComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        Bundle bundle = getActivity().getIntent().getExtras();
        favorId = bundle.getString(FavorDetails.FAVOR_ID_KEY);
        distance = bundle.getFloat(FavorDetails.FAVOR_DISTANCE_KEY);

        View view = inflater.inflate(R.layout.fragment_favor_details, container, false);
        ButterKnife.bind(this, view);

        boolean isOwner = favorVM.isFavorOwner(this.favorOwner);
        if (isOwner)
            fab.setContentDescription(getString(R.string.mark_favor_done_button));
        else
            fab.setContentDescription(getString(R.string.do_favor_button));
        return view;
    }

    @Override
    public void onResume() {
        BindVM();
        super.onResume();
    }

    private void BindVM() {
        favorVM.getFavorById(favorId).subscribe(this::updateUI,
                throwable -> Snackbar.make(coordinatorLayout,
                        R.string.something_wrong_error_message, Snackbar.LENGTH_SHORT).show());
    }

    private void updateUI(Favor favor) {
        favorTitle.setText(favor.title());
        favorDetails.setText(favor.description());
        distanceText.setText(formatDistance(distance));
        authorName.setText(favor.owner().firstName());
        authorPhoto.setImageURI(favor.owner().avatar());
        favorOwner = favor.owner().id();
        if (favorOwner != null)
            fab.setEnabled(true);
    }

    private String formatDistance(float distance) {
        if (distance <= 10)
            return getString(R.string.just_her);
        else if (distance < 1000)
            return getString(R.string.meters_away,
                    Math.round(distance));
        else
            return getString(R.string.km_away,
                    Math.round(Math.round(distance / 1000)));
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {

        boolean isOwner = favorVM.isFavorOwner(this.favorOwner);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity(),
                R.style.AppCompatAlertDialogStyle);
        builder.setTitle(R.string.confirmation);
        builder.setMessage((isOwner) ? getString(R.string.favor_done_confirmation_messgae)
                : getString(R.string.favor_benefactor_confirmation_message));
        if (isOwner)
            builder.setPositiveButton(R.string.ok,
                    (dialog, which) -> markFavorAsDone());
        else
            builder.setPositiveButton(R.string.ok,
                    (dialog, which) -> markFavorAsWillDo());

        builder.setNegativeButton(R.string.cancel, null);
        builder.show();

    }

    private void markFavorAsDone() {
        favorVM.markFavorAsDone(favorId).subscribe(
                favor -> updateUIAfterFavorDone(),
                throwable -> Toast
                        .makeText(this.getActivity(), R.string.something_wrong_error_message,
                                Toast.LENGTH_SHORT)
                        .show());

    }

    private void markFavorAsWillDo() {
        favorVM.addBenefactorToFavor().subscribe(
                favor -> Toast
                        .makeText(this.getActivity(),
                                R.string.benefactor_success_message,
                                Toast.LENGTH_SHORT)
                        .show(),
                throwable -> Toast
                        .makeText(this.getActivity(), R.string.something_wrong_error_message,
                                Toast.LENGTH_SHORT)
                        .show());

    }

    private void updateUIAfterFavorDone() {
        Toast.makeText(this.getActivity(), R.string.favor_marked_done, Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }
}
