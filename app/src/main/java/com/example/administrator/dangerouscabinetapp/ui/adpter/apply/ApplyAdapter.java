package com.example.administrator.dangerouscabinetapp.ui.adpter.apply;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.dangerouscabinetapp.R;
import com.example.administrator.dangerouscabinetapp.item.SqViewType;

import java.util.List;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/20 0020 17:10
 * Description:
 */
public class ApplyAdapter extends BaseMultiItemQuickAdapter<SqViewType, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ApplyAdapter(List<SqViewType> data) {
        super(data);
        addItemType(SqViewType.SL_TYPE_HEAD, R.layout.cg_head);
        addItemType(SqViewType.SL_TYPE_DETAIL, R.layout.cg_detail);
        addItemType(SqViewType.SL_TYPE_ADD, R.layout.cg_add);
        addItemType(SqViewType.SL_TYPE_EXPLAIN, R.layout.cg_explain);
    }

    @Override
    protected void convert(BaseViewHolder helper, SqViewType item) {
        switch (helper.getItemViewType()) {
            case SqViewType.SL_TYPE_HEAD:
                break;
            case SqViewType.SL_TYPE_ADD:
                helper.addOnClickListener(R.id.btn_add);
                break;
            case SqViewType.SL_TYPE_DETAIL:
                helper.setText(R.id.tv_detail_id, "物品明细(" + helper.getAdapterPosition() + ")");
                helper.setText(R.id.et_detail_name, item.getList().get(helper.getAdapterPosition() - 1).getName());
                helper.setText(R.id.et_num, item.getList().get(helper.getAdapterPosition() - 1).getNum());
                helper.addOnClickListener(R.id.btn_del);
                break;
        }
    }
}
