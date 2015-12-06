package net.numa08.gistlist.widget;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.numa08.gistlist.R;
import net.numa08.gistlist.realm.models.Gist;
import net.numa08.gistlist.realm.models.Owner;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_gist)
public class GistView extends RelativeLayout {

    @ViewById(R.id.avatar)
    ImageView avatar;
    @ViewById(R.id.gist_title)
    TextView title;
    @ViewById(R.id.gist_create_ago)
    TextView createdAgo;
    @ViewById(R.id.gist_description)
    TextView description;

    Gist gist;
    Picasso picasso;

    private boolean inflated;

    public GistView(Context context) {
        super(context);
    }

    public GistView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GistView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflated = true;
    }

    public Gist getGist() {
        return gist;
    }

    public void setGist(Gist gist) {
        this.gist = gist;
        showGist();
    }

    public Picasso getPicasso() {
        return picasso;
    }

    public void setPicasso(Picasso picasso) {
        this.picasso = picasso;
    }

    @AfterViews
    void showGist() {
        if (picasso == null) {
            return;
        }
        if (gist == null) {
            return;
        }
        if (!inflated) {
            return;
        }
        final String ownerName;
        if (gist.getOwner() != null) {
            final Owner owner = gist.getOwner();
            ownerName = owner.getLogin();
            if (!TextUtils.isEmpty(owner.getAvatarUrl())) {
                picasso.load(owner.getAvatarUrl())
                        .fit()
                        .into(avatar);
            }
        } else {
            ownerName = "anonymous";
        }
        final String fileName;
        if (gist.getFiles() != null && gist.getFiles().size() > 0) {
            fileName = gist.getFiles().get(0).getName();
        } else {
            fileName = "";
        }

        final String title = ownerName + " / " + fileName;
        this.title.setText(title);

        final long now = System.currentTimeMillis();
        createdAgo.setText(DateUtils.getRelativeTimeSpanString(gist.getCreatedAt().getTime(), now, DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE));

        if (!TextUtils.isEmpty(gist.getDescription())) {
            description.setText(gist.getDescription());
        }
    }
}
