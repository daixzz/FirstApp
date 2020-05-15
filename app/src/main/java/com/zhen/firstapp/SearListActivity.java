package com.zhen.firstapp;

/*
public class SearListActivity extends ListActivity implements Runnable {

    private ListView listView;
    String data[]= {"one","two","three"};
    private  String TAG = "searlist";
    Handler handler;
    private ArrayList<HashMap<String,String>> listItems;
    private SimpleAdapter listItemAdapter;
    private int msgwhat = 7;
    TextView textview;
    String updateDate = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        this.setListAdapter(listItemAdapter);
        textview=(EditText)findViewById(R.id.Search);

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String todayStr= sdf.format(today);
        String abc[] = todayStr.split("-");
        int a = Integer.parseInt(abc[0]);
        int b = Integer.parseInt(abc[1]);
        int c = Integer.parseInt(abc[2]);
        int d =(a*365+b*30+c)/7;


        String cde[] = updateDate.split("-");
        int e = Integer.parseInt(abc[0]);
        int f = Integer.parseInt(abc[1]);
        int g = Integer.parseInt(abc[2]);
        int h = (e&365+f*30+g)/7;
        //        setContentView(R.layout.activity_sear_list);
//        listView = (ListView)findViewById(R.id.Search_list);

            //        ListView listView =  findViewById(R.id.Search_list);
//        String data[]={"111","222","333"};
//        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
//        listView.setAdapter(adapter);
//        final ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
//        setListAdapter(adapter);

        Thread t = new Thread(this);
        t.start();

        List<String> list1=new ArrayList<String>();
        for(int i = 1;i<100;i++){
            list1.add("item" + i);
        }
        Log.i(TAG, "onCreate: sp updateDate=" + updateDate);
        Log.i(TAG, "onCreate: sp todayStr=" + todayStr);




        if(d!=h){
            Log.i(TAG, "onCreate: :" + "需要更新");

            //开启子线程
            Thread thrad = new Thread(this);
            t.start();
        }
        else{
            Log.i(TAG, "onCreate: :" + "不需要更新");

        }

        handler = new Handler() {
            public void handleMessage(Message msg) {

                if (msg.what == 7) {
                    List<HashMap<String,String>> retList = (List<HashMap<String,String>>) msg.obj;
                    listItemAdapter = new SimpleAdapter(SearListActivity.this, retList,
                            R.layout.list_item2,
                            new String[]{"SearchTitle"},
                            new int[]{R.id.SearchTitle}

                            Bundle bd1 = (Bundle) msg.obj;

                    SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    );
                    setListAdapter(listItemAdapter);
                }
                super.handleMessage(msg);
            }
        };

//        getListView().setOnItemClickListener(this);

    }
    private void initListView(){
        listItems = new ArrayList<HashMap<String,String>>();
        for(int i = 0;i < 10;i++){
            HashMap<String,String> map = new HashMap<String,String>() ;
            map.put("SearchTitle","Search" + i);
            listItems.add(map);
        }
        listItemAdapter = new SimpleAdapter(this,listItems,
                R.layout.activity_sear_list,
                new String[] {"SearchTitle"},
                new int [] {R.id.SearchTitle}
        );
    }
    public void run() {
        Log.i("thread","run...");
        String str=textview.getText().toString();

        List<HashMap<String, String>> retList = new ArrayList<HashMap<String, String>>();
        Document doc = null;
        try {
//            Thread.sleep(1000);
            doc = Jsoup.connect("https://it.swufe.edu.cn/index/tzgg/56.htm").get();
            Log.i(TAG, "run:" + doc.title());
            Elements divs = doc.getElementsByTag("div");

*/
/*            int i =1;
            for(Element div : divs){
                Log.i(TAG,"run:div["+i+"]=" + div);
                i++;
            }*//*


            Element divs19 = divs.get(18);
            Log.i(TAG,"run:div19=" + divs19);

            //获取span中的数据
            Elements spans = divs19.getElementsByTag("span");
            for(int x = 0;x<spans.size();x+=2){
                Element span1 = spans.get(x);

*/
/*
                    Log.i(TAG, "run:text=" + span1.text());
*//*

                    String textStr = span1.text();

                    int a = textStr.indexOf(str);
                    if(a !=-1){
                        HashMap<String,String> map = new HashMap<String, String>();
                        map.put("SearchTitle",textStr);

                        retList.add(map);
                        Log.i("span",textStr);
                        Log.i(TAG, "run:text=" + span1.text());

                        HashMap<String,String> map = new HashMap<String, String>();
                        map.put("SearchTitle",textStr);

                        retList.add(map);
                        Log.i("span",textStr);
                    }

            }
            for(Element span:spans) {
                Log.i(TAG, "run:spans" + span);
                Log.i(TAG, "run:text" + span.text());
                Log.i(TAG, "run:html" + span.html());

            }


//
//                HashMap<String,String> map = new HashMap<String, String>();
//                map.put("ItemTitle",str1);
//                map.put("ItemDetail",val);
//                retList.add(map);
        }


//        } catch (InterruptedException e) {
//            e.printStackTrace();
         catch (IOException e) {
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(7);
        msg.obj = retList;
        handler.sendMessage(msg);
    }



}

*/
