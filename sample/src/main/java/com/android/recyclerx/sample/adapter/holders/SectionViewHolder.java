package com.android.recyclerx.sample.adapter.holders;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.recyclerx.adapter.holder.BaseViewHolder;
import com.android.recyclerx.callback.SectionChangeListener;
import com.android.recyclerx.callback.click.RecyclerViewItemClickListener;
import com.android.recyclerx.sample.R;
import com.android.recyclerx.sample.adapter.section.MainSection;
import com.android.recyclerx.entity.Section;

/**
 * @author Vadim Firsov
 */
public class SectionViewHolder extends BaseViewHolder<MainSection> implements SectionChangeListener {

    private TextView mTitleTextView;
    private ImageView mArrowImageView;

    public SectionViewHolder(@NonNull View itemView, @NonNull RecyclerViewItemClickListener listener) {
        super(itemView, listener);
        mTitleTextView = itemView.findViewById(R.id.section_item_title_text_view);
        mArrowImageView = itemView.findViewById(R.id.section_item_arrow_image_view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void bindViewHolderWithEntity(@Nullable MainSection section) {
        if (section != null) {
            section.setSectionChangeListener(this);
            mTitleTextView.setText(section.getTitle());
            mArrowImageView.setRotation(section.isExpanded() ? 180 : 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S extends Section> void onChanged(S s) {
        mArrowImageView.setRotation(s.isExpanded() ? 180 : 0);
    }
}