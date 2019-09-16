package in.xenonstudio.bachat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements OnChartValueSelectedListener {

    TextView datatext,datatitle,datavalue;
    PieChart pieChart;
    PieDataSet dataSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        datatext = (TextView)findViewById(R.id.datatext);

        datatitle = (TextView)findViewById(R.id.datatitle);

        datavalue = (TextView)findViewById(R.id.datavalue);

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
    // xIndex (even if from different DataSets), since no values can be
    // drawn above each other.
    ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(24f, 0));
        yvalues.add(new Entry(15f, 1));
        yvalues.add(new Entry(19f, 2));
        yvalues.add(new Entry(22f, 3));
        yvalues.add(new Entry(20f, 4));
        //yvalues.add(new Entry(17f, 5));

     dataSet = new PieDataSet(yvalues, "Balances");

    ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Monthly Expenses");
        xVals.add("Phonepe");
        xVals.add("Uber");
        xVals.add("Paytm");
        xVals.add("Savings");
       // xVals.add("Ola");


    PieData data = new PieData(xVals, dataSet);
    // In Percentage term
        data.setValueFormatter(new PercentFormatter());
    // Default value
    //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(data);
        pieChart.setDescription("Savings");

        //HIDING VALUES
        dataSet.setDrawValues(false);
        pieChart.setDrawSliceText(false);


        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setHoleRadius(25f);

        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);

        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.setOnChartValueSelectedListener(this);

        pieChart.setTouchEnabled(true);
        pieChart.animateXY(1400, 1400);

      /*  MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker);
        pieChart.setMarkerView(mv);*/
}

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.datalayout);
        layout.setVisibility(View.VISIBLE);
        View view1 = (View) findViewById(R.id.view1);
        view1.setVisibility(View.VISIBLE);
        View view2 = (View) findViewById(R.id.view2);
        view2.setVisibility(View.VISIBLE);

        int position =  e.getXIndex();
        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
        dataSet.setDrawValues(false);
        pieChart.setDrawSliceText(true);
        //XValue
      /*  final String selectedValue=pieChart.getX().get(position);
        Log.d("selctdX", selectedValue);
        Toast.makeText(this, ""+selectedValue, Toast.LENGTH_SHORT).show();*/

        final String selectedYValuew = String.valueOf(e.getXIndex());
        Toast.makeText(this, ""+selectedYValuew+dataSetIndex, Toast.LENGTH_SHORT).show();

        //YValue
        final String selectedYValue = String.valueOf(e.getVal());

        Toast.makeText(this, ""+selectedYValue, Toast.LENGTH_SHORT).show();


        // To remove slice text
      /*  pieChart.setDrawMarkers(false); // To remove markers when click
        pieChart.setDrawEntryLabels(false); // To remove labels from piece of pie
        pieChart.getDescription().setEnabled(false);*/
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);

        float datatt = e.getVal();
        float pos = e.getXIndex();

        String symbol = "₹";
       // int finalpos = Integer.parseInt((""+pos));
//        Toast.makeText(this, ""+pos, Toast.LENGTH_SHORT).show();
        datatext.setText(""+datatt+"%");
        if (pos==0.0){
            datatitle.setText("Expenses");
            //DUMMY VALUE FOR NOW

            datavalue.setText(symbol+"3550");
        }
        else  if (pos==1.0){
            datatitle.setText("Phonepe");
            datavalue.setText(symbol+"910");

        }
        else  if (pos==2.0){
            datatitle.setText("Uber");
            datavalue.setText(symbol+"320");
        }
        else  if (pos==3.0){
            datatitle.setText("Paytm");
            datavalue.setText(symbol+"2320");
        }
        else  if (pos==4.0){
            datatitle.setText("Savings");
            datavalue.setText(symbol+"1200");
        }

        //datatitle.setText(""+title);Monthly Expenses");
        //        xVals.add("Phonepe");
        //        xVals.add("Uber");
        //        xVals.add("Paytm");
        //        xVals.add("Savings");
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

}
