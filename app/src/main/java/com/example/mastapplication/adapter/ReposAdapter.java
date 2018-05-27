package com.example.mastapplication.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mastapplication.R;
import com.example.mastapplication.activity.LoginActivity;
import com.example.mastapplication.fragments.LoginFragment;
import com.example.mastapplication.fragments.WebFragment;
import com.example.mastapplication.model.RepositoryRes;
import com.example.mastapplication.util.MastConstants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vandanahegde on 27/05/18.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {
    private Context context;
    private View view;
    List<RepositoryRes.RepositoryData> repositoryData;
    private AlertDialog alertDialogDesc;

    public ReposAdapter(Context context, List<RepositoryRes.RepositoryData> repositoryData) {
        this.context = context;
        this.repositoryData = repositoryData;
    }

    @Override
    public ReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.adapter_respos, parent, false);
        return new ReposViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReposViewHolder holder, final int position) {
        if (repositoryData.get(position) != null) {
            if (repositoryData.get(position).getName() != null)
                holder.projectNameTV.setText(repositoryData.get(position).getName());
            holder.sizeTV.setText(repositoryData.get(position).getSize() + "");
            holder.watchersTV.setText(repositoryData.get(position).getWatchers() + "");
            holder.issuesTV.setText(repositoryData.get(position).getOpen_issues_count() + "");

            if (repositoryData.get(position).getHtml_url() != null) {
                ClickableSpan htmlLink = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {
                        WebFragment webFragment = new WebFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString(MastConstants.HTML_URL, repositoryData.get(position).getHtml_url());
                        webFragment.setArguments(bundle);
                        ((LoginActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, webFragment).addToBackStack("ListFragment").commit();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(true);
                        ds.setColor(context.getResources().getColor(R.color.colorPrimaryDark));
                    }
                };

                SpannableString termsText = new SpannableString(repositoryData.get(position).getHtml_url());
                termsText.setSpan(htmlLink, 0, (repositoryData.get(position).getHtml_url().length()), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.htmlUrlTV.setMovementMethod(LinkMovementMethod.getInstance());
                holder.htmlUrlTV.setText(termsText);
            }

            holder.contentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialogForServiceLocations(repositoryData.get(position));
                }
            });
        }
    }

    private void showDialogForServiceLocations(RepositoryRes.RepositoryData repositoryData) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = ((LoginActivity) context).getLayoutInflater();
            View view = inflater.inflate(R.layout.layout_desc, null, false);
            TextView nameTV = view.findViewById(R.id.project_value_text_view);
            if(repositoryData.getName() != null) nameTV.setText(repositoryData.getName());
            TextView descTV = view.findViewById(R.id.project_desc_text_view);
            if(repositoryData.getDescription() != null) descTV.setText(repositoryData.getDescription());
            ImageView imageView = view.findViewById(R.id.avatar_image_view);
            Picasso.with(context).load(repositoryData.getOwner().getAvatar_url()).placeholder(R.color.colorPeach).into(imageView);
            builder.setView(view);

            alertDialogDesc = builder.create();
            alertDialogDesc.setCanceledOnTouchOutside(true);
            alertDialogDesc.setCancelable(true);
            if (!((LoginActivity) context).isFinishing()) alertDialogDesc.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (repositoryData != null && repositoryData.size() > 0)
            return repositoryData.size();
        else return 0;
    }


    class ReposViewHolder extends RecyclerView.ViewHolder {
        private TextView projectNameTV, htmlUrlTV, sizeTV, watchersTV, issuesTV;
        private LinearLayout contentLayout;

        ReposViewHolder(View itemView) {
            super(itemView);
            projectNameTV = itemView.findViewById(R.id.project_name_text_view);
            htmlUrlTV = itemView.findViewById(R.id.html_url_text_view);
            sizeTV = itemView.findViewById(R.id.size_text_view);
            watchersTV = itemView.findViewById(R.id.watchers_text_view);
            issuesTV = itemView.findViewById(R.id.issues_text_view);
            contentLayout = itemView.findViewById(R.id.content_layout);
        }

    }
}
