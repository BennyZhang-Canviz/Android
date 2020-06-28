package com.zsx.examples.adapterexample

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zsx.examples.R


class MyAdapter(layoutResId: Int, data: MutableList<Student>) :
    BaseQuickAdapter<Student, BaseViewHolder>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: Student) {
        item.let{
            holder.setText(R.id.tvName, item.name)
                .setText(R.id.tvAddress,item.address)
                .setText(R.id.tvAge,item.age.toString())
        }
    }


}
