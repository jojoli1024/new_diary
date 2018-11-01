package com.jojo.diary.diary;

import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class DiaryItemHelper extends Observable {
    private List<IDairyRow> diaryItemList;
    private LinearLayout itemContentLayout;

    public DiaryItemHelper(LinearLayout itemContentLayout){
        this.itemContentLayout = itemContentLayout;
        this.diaryItemList = new ArrayList<>();
    }

    public int getItemSize() {
        return diaryItemList.size();
    }

}
