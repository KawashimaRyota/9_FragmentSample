package com.example.fragmentsaample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuListFragment extends Fragment {
    /**
     * このフラグメントが所属するアクティビティオブジェクト
     */
    private Activity _parentActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //親クラスのonCreate()の呼び出し
        super.onCreate(savedInstanceState);
        //所属するアクティビティオブジェクトを取得
        _parentActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //フラグメントで表示する画面をXMLファイルからインフレートする
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);
        //画面部品ListViewを取得
        ListView lvMenu = view.findViewById(R.id.lvMenu);
        //SimpleAdapterで使用するLisオブジェクトを用意
        List<Map<String, String>> menuList = new ArrayList<>();
        //からあげ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        Map<String, String> menu = new HashMap<>();
        menu.put("name", "からあげ定食");
        menu.put("price", "800円");
        menuList.add(menu);
        //ハンバーグ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", "850円");
        menuList.add(menu);
        //生姜焼き定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = new HashMap<>();
        menu.put("name", "生姜焼き定食");
        menu.put("price", "850円");
        menuList.add(menu);
        //ステーキ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = new HashMap<>();
        menu.put("name", "ステーキ定食");
        menu.put("price", "1000円");
        menuList.add(menu);
        //野菜炒め定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = new HashMap<>();
        menu.put("name", "野菜炒め定食");
        menu.put("price", "750円");
        menuList.add(menu);
        //とんかつ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = new HashMap<>();
        menu.put("name", "とんかつ定食");
        menu.put("price", "900円");
        menuList.add(menu);
        //ミンチかつ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = new HashMap<>();
        menu.put("name", "ミンチかつ定食");
        menu.put("price", "850円");
        menuList.add(menu);
        //チキンカツ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = new HashMap<>();
        menu.put("name", "チキンカツ定食");
        menu.put("price", "900円");
        menuList.add(menu);
        //コロッケ定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = new HashMap<>();
        menu.put("name", "コロッケ定食");
        menu.put("price", "750円");
        menuList.add(menu);
        //オールスター定食のデータを格納するMapオブジェクトの用意とmenuListへのデータ登録
        menu = new HashMap<>();
        menu.put("name", "オールスター定食");
        menu.put("price", "1200円");
        menuList.add(menu);

        //SimpleAdapter第4引数fromデータの用意
        String[] from = {"name", "price"};
        //SimpleAdapter第5引数toデータの用意
        int[] to = {android.R.id.text1, android.R.id.text2};
        //SimpleAdapterを生成
        SimpleAdapter adapter = new SimpleAdapter(_parentActivity, menuList, android.R.layout.simple_list_item_2, from, to);
        //アダプタの登録
        lvMenu.setAdapter(adapter);
        //リスナの登録
        lvMenu.setOnItemClickListener(new ListItemClickListener());
        //インフレートされた画面を戻り値として返す
        return view;
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //タップされた行のデータを取得。SimpleAdapterでは1行分のデータはMap型
            Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
            //定食名と金額を取得
            String menuName = item.get("name");
            String menuPrice = item.get("price");
            //インテントオブジェクトを生成
            Intent intent = new Intent(_parentActivity, MenuThanksActivity.class);
            //第2画面に送るデータを格納
            intent.putExtra("menuName", menuName);
            intent.putExtra("menuPrice", menuPrice);
            //第2画面の起動
            startActivity(intent);
        }
    }
}
