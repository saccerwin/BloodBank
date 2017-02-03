package com.example.admin.bloodbank.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateFragment;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Admin on 02/02/2017.
 */

public class StatisticalFragment extends TemplateFragment {

    private CombinedChart combinedChart;
    private final int itemCount = 12;
    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistical, null);
    }

    @Override
    protected void initUI(View view, Bundle savedInstanceState) {
        combinedChart = (CombinedChart) view.findViewById(R.id.chartStatisticalYearDonationBlood);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        setupCombinedChart();
    }

    private void setupCombinedChart() {
        combinedChart.setBackgroundColor(Color.WHITE);
        // draw bars behind lines
        combinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });
        Legend l = combinedChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        YAxis rightAxis = combinedChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        YAxis leftAxis = combinedChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        XAxis xAxis = combinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        CombinedData data = new CombinedData();
        data.setData(generateLineData());
        xAxis.setAxisMaximum(data.getXMax() + 0.25f);
        combinedChart.setData(data);
        combinedChart.invalidate();
        combinedChart.animateY(2000);
        combinedChart.getDescription().setEnabled(false);
    }

    private LineData generateLineData() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Tháng 1");
        labels.add("Tháng 2");
        labels.add("Tháng 3");
        labels.add("Tháng 4");
        labels.add("Tháng 5");
        labels.add("Tháng 6");
        labels.add("Tháng 7");
        labels.add("Tháng 8");
        labels.add("Tháng 9");
        labels.add("Tháng 10");
        labels.add("Tháng 11");
        labels.add("Tháng 12");
        LineData d = new LineData();
        ArrayList<Entry> entries = new ArrayList<>();
        for (int index = 1; index <= itemCount; index++) {
            entries.add(new Entry( index, getRandom(10, 20)));
        }
        LineDataSet set = new LineDataSet(entries, "Số người hiến máu từng tháng");
        set.setColor(Color.GREEN);
        set.setLineWidth(2f);
        set.setCircleColor(Color.GREEN);
        set.setCircleRadius(5f);
        set.setFillColor(Color.GRAY);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.DKGRAY);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);
        return d;
    }





    private int getRandom(int i, int i1) {
        Random r = new Random();
        return r.nextInt(i1 - i) + i;
    }


}
