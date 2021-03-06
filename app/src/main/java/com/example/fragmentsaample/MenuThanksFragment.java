package com.example.fragmentsaample;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MenuThanksFragment extends Fragment {


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

    /**
     * 大画面かどうかの判定フラグ。
     * trueが大画面、falseが通常画面。
     * 判定ロジックは同一画面に注文完了表示用フレームレイアウトが存在するかで行う。
     */
    private boolean _isLayoutXLarge = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //フラグメントで表示する画面をXMLファイルからインフレートする
        View view = inflater.inflate(R.layout.fragment_menu_thanks, container, false);

        //Bundleオブジェクトを宣言
        Bundle extras;
        //大画面の場合
        if(_isLayoutXLarge) {
            //フラグメントに埋め込まれた引き継ぎデータを取得
            extras = getArguments();
        }
        //通常画面の場合
        else {
            //所属するアクティビティからインテントを取得
            Intent intent = _parentActivity.getIntent();
            //インテントから引き継ぎデータをまとめたもの（Bundleオブジェクト）を取得
            extras = intent.getExtras();
        }

        //注文した定食名と金額変数を用意。引き継ぎデータがうまく取得できなかった時のために""で初期化
        String menuName = "";
        String menuPrice = "";

        //引き継ぎデータが存在すれば
        if(extras != null) {
            //定食名と金額を取得
            menuName = extras.getString("menuName");
            menuPrice = extras.getString("menuPrice");
        }
        //定食名と金額を表示させるTextViewを取得
        TextView tvMenuName = view.findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = view.findViewById(R.id.tvMenuPrice);
        //TextViewに定食名と金額を表示
        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);

        //戻るボタンを取得
        Button btBackButton = view.findViewById(R.id.btBackButton);
        //戻るボタンにリスナを登録
        btBackButton.setOnClickListener(new ButtonClickListner());

        //インフレートされた画面を戻り値として返す
        return view;
    }

    /**
     * ボタンが押された時の処理が記述されたクラス
     */
    private class ButtonClickListner implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //大画面の場合
            if(_isLayoutXLarge) {
                //フラグメントマネージャーを取得
                FragmentManager manager = getFragmentManager();
                //フラグメントトランザクション
                FragmentTransaction transaction = manager.beginTransaction();
                //自分自身を削除
                transaction.remove(MenuThanksFragment.this);
                //フラグメントトランザクションのコミット
                transaction.commit();
            }
            else {
                //自分が所属するアクティビティを終了
                _parentActivity.finish();
            }
        }
    }

}
