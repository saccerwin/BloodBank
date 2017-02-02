package com.example.admin.bloodbank.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.bloodbank.R;
import com.example.admin.bloodbank.abstracts.TemplateFragment;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Admin on 02/02/2017.
 */

public class StatisticalFragment extends TemplateFragment {

    private CombinedChart combinedChart;
    private final int itemCount = 12;
    private PieChart pieChart;
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
        pieChart = (PieChart)view.findViewById(R.id.pieChart);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {


        setupPieChart();
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

    private void setupPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(4f,0));
        entries.add(new PieEntry(5f,0));
        entries.add(new PieEntry(6f,0));
        entries.add(new PieEntry(4f,0));
        entries.add(new PieEntry(4f,0));
        entries.add(new PieEntry(4f,0));
        PieDataSet dataSet = new PieDataSet(entries,"Số lượng tiểu cầu máu theo tháng");
        dataSet.setSliceSpace(1);
        dataSet.setSelectionShift(4);

        ArrayList<String> label = new ArrayList<String>();
        label.add("Tháng 1");
        label.add("Tháng 1");
        label.add("Tháng 1");
        label.add("Tháng 1");
        label.add("Tháng 1");
        label.add("Tháng 1");

        for(int i = 0; i < label.size(); i++) {

        }
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.animateY(5000);
        pieChart.setDrawCenterText(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setEntryLabelColor(Color.BLUE);
        pieChart.setEntryLabelTextSize(15f);
    }

    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int index = 0; index < itemCount; index++)
            entries.add(new Entry(index + 0.5f, getRandom(5, 20)));
        LineDataSet set = new LineDataSet(entries, "Số người hiến máu từng tháng");
        set.setColor(Color.CYAN);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
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
