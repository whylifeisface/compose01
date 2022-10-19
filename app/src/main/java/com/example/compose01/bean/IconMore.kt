package com.example.compose01.bean

import android.graphics.drawable.Icon

class IconMore(val text: String) {


    //    多窗口  书签 历史 阅读模式 刷新 添加书签 显示隐藏 隐身模式 举报 复制网址 无图模式 意见反馈 分享 设置退出
    //
    companion object {
        fun myIniy(): ArrayList<IconMore> {
            val ListItem: ArrayList<IconMore> = ArrayList()
            val list = listOf<String>(
                "多窗口",
                "书签",
                "历史",
                "阅读模式",
                "刷新",
                "添加书签",
                "显示隐藏",
                "隐身模式",
                "举报",
                "复制网址",
                "无图模式",
                "意见反馈",
                "分享",
                "设置"
                ,"退出"
            )
            for (i in 0..list.size - 1) {
                val get = list.get(i)
                val iconMore = IconMore(text = get)
                ListItem.add(iconMore)
            }
            return ListItem
        }
    }

}
