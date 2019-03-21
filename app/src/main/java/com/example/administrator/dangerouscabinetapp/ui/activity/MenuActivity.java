package com.example.administrator.dangerouscabinetapp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.administrator.dangerouscabinetapp.MyApp;
import com.example.administrator.dangerouscabinetapp.R;
import com.example.administrator.dangerouscabinetapp.ui.adpter.menu.ContactsSortAdapter;
import com.example.administrator.dangerouscabinetapp.entity.SortModel;
import com.example.administrator.dangerouscabinetapp.entity.SortToken;
import com.example.administrator.dangerouscabinetapp.item.GoodsItem;
import com.example.administrator.dangerouscabinetapp.utils.MessageEvent;
import com.example.administrator.dangerouscabinetapp.utils.menu.CharacterParser;
import com.example.administrator.dangerouscabinetapp.utils.menu.PinyinComparator;
import com.example.administrator.dangerouscabinetapp.weight.menu.SideBar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author: create by ZhongMing
 * Time: 2019/3/21 0021 13:40
 * Description:
 */
public class MenuActivity extends AppCompatActivity {
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ivClearText)
    ImageView ivClearText;
    @BindView(R.id.lv_contacts)
    ListView mListView;
    @BindView(R.id.dialog)
    TextView dialog;
    @BindView(R.id.sidrbar)
    SideBar sideBar;
    ArrayList<SortModel> fileterList;
    @BindView(R.id.img_back)
    ImageView imgBack;
    private List<SortModel> mAllContactsList;
    private ContactsSortAdapter adapter;
    Context mContext;

    private static CharSequence _input;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu_list);
        ButterKnife.bind(this);
        mContext = MenuActivity.this;
        init();
    }

    private void init() {
        initView();
        initListener();
        loadContacts();
        initEvent();
    }

    private void initEvent() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (fileterList.size() > 0) {
                    showDialog(fileterList.get(i).getName());
                } else {
                    showDialog(mAllContactsList.get(i).getName());
                }
            }
        });
    }

    private void initListener() {
        /** 清除输入字符 **/
        ivClearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch.setText("");
                fileterList.clear();
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable e) {
                String content = etSearch.getText().toString();
                if ("".equals(content)) {
                    ivClearText.setVisibility(View.INVISIBLE);
                } else {
                    ivClearText.setVisibility(View.VISIBLE);
                }
                if (content.length() > 0) {
                    fileterList = (ArrayList<SortModel>) search(content);
                    adapter.updateListView(fileterList);
                    // mAdapter.updateData(mContacts);
                } else {
                    adapter.updateListView(mAllContactsList);
                }
                mListView.setSelection(0);
            }
        });

        // 设置右侧[A-Z]快速导航栏触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mListView.setSelection(position);
                }
            }
        });
        // item事件
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long arg3) {
                ContactsSortAdapter.ViewHolder viewHolder = (ContactsSortAdapter.ViewHolder) view.getTag();
                adapter.toggleChecked(position);
            }
        });

    }

    private void initView() {
        sideBar.setTextView(dialog);
        /** 给ListView设置adapter **/
        characterParser = CharacterParser.getInstance();
        mAllContactsList = new ArrayList<>();
        fileterList = new ArrayList<>();
        pinyinComparator = new PinyinComparator();
        Collections.sort(mAllContactsList, pinyinComparator);// 根据a-z进行排序源数据
        adapter = new ContactsSortAdapter(this, mAllContactsList);
        mListView.setAdapter(adapter);
    }

    /**
     * 加载数据
     */
    private void loadContacts() {
        setData("环己烷");
        setData("甲酸");
        setData("碳酸钙");
        setData("六水合硝酸钴");
        setData("硫酸锰");
        setData("氢氧化钠");
        setData("氟化钠");
        setData("碳酸氢钠");
        setData("硫酸镁");
        setData("COOCH2OOMNC");
        setData("聚氯乙烯");
        runOnUiThread(new Runnable() {
            public void run() {
                Collections.sort(mAllContactsList, pinyinComparator);
                adapter.updateListView(mAllContactsList);
            }
        });
    }

    private void setData(String name) {
        String num = "5468795";
        String sortKey = name.substring(0, 1).trim();
        SortModel sortModel = new SortModel(name, num, sortKey);
        String book = getSortLetterBySortKey(getSortLetter(sortKey));
        if (book == null || book.equals("#") || book.equals("")) {
            book = "#";
        }
        sortModel.sortLetters = book;
        sortModel.sortToken = parseSortKey(book);
        mAllContactsList.add(sortModel);
    }

    /**
     * 名字转拼音,取首字母
     *
     * @param name
     * @return
     */
    private String getSortLetter(String name) {
        String letter = "#";
        if (name == null) {
            return letter;
        }
        // 汉字转换成拼音
        String pinyin = characterParser.getSelling(name);
//        String pinyin = PinYin.getPinYin(name);
//        Log.i("main", "pinyin:" + pinyin);
        String sortString = pinyin.substring(0, 1).toUpperCase(Locale.CHINESE);

        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            letter = sortString.toUpperCase(Locale.CHINESE);
        }
        return letter;
    }

    /**
     * 取sort_key的首字母
     *
     * @param sortKey
     * @return
     */
    private String getSortLetterBySortKey(String sortKey) {
        if (sortKey == null || "".equals(sortKey.trim())) {
            return null;
        }
        String letter = "#";
        // 汉字转换成拼音
        String sortString = sortKey.trim().substring(0, 1).toUpperCase(Locale.CHINESE);
        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            letter = sortString.toUpperCase(Locale.CHINESE);
        }
        return letter;
    }

    /**
     * 模糊查询
     *
     * @param str
     * @return
     */
    private List<SortModel> search(String str) {
        List<SortModel> filterList = new ArrayList<SortModel>();// 过滤后的list
        // if (str.matches("^([0-9]|[/+])*$")) {// 正则表达式 匹配号码
        if (str.matches("^([0-9]|[/+]).*")) {// 正则表达式
            // 匹配以数字或者加号开头的字符串(包括了带空格及-分割的号码)
            String simpleStr = str.replaceAll("\\-|\\s", "");
            for (SortModel contact : mAllContactsList) {
                if (contact.number != null && contact.name != null) {
                    if (contact.simpleNumber.contains(simpleStr) || contact.name.contains(str)) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        } else {
            for (SortModel contact : mAllContactsList) {
                if (contact.number != null && contact.name != null) {
                    // 姓名全匹配,姓名首字母简拼匹配,姓名全字母匹配
                    if (contact.name.toLowerCase(Locale.CHINESE).contains(str.toLowerCase(Locale.CHINESE))
                            || contact.sortKey.toLowerCase(Locale.CHINESE).replace(" ", "")
                            .contains(str.toLowerCase(Locale.CHINESE))
                            || contact.sortToken.simpleSpell.toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE))
                            || contact.sortToken.wholeSpell.toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE))) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        }
        return filterList;
    }

    String chReg = "[\\u4E00-\\u9FA5]+";// 中文字符串匹配

    // String chReg="[^\\u4E00-\\u9FA5]";//除中文外的字符匹配

    /**
     * 解析sort_key,封装简拼,全拼
     *
     * @param sortKey
     * @return
     */
    public SortToken parseSortKey(String sortKey) {
        SortToken token = new SortToken();
        if (sortKey != null && sortKey.length() > 0) {
            // 其中包含的中文字符
            String[] enStrs = sortKey.replace(" ", "").split(chReg);
            for (int i = 0, length = enStrs.length; i < length; i++) {
                if (enStrs[i].length() > 0) {
                    // 拼接简拼
                    token.simpleSpell += enStrs[i].charAt(0);
                    token.wholeSpell += enStrs[i];
                }
            }
        }
        return token;
    }

    public void showDialog(final String msg) {
        new MaterialDialog.Builder(mContext)
                .title(msg)
                .inputRangeRes(2, 20, R.color.red)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("请输入" + msg + "的数量", null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        MessageEvent messageEvent = new MessageEvent(MyApp.CHOOSE_GOODS_NUMBER);
                        messageEvent.setGoodsItem(new GoodsItem("234324", msg, String.valueOf(input)));
                        EventBus.getDefault().post(messageEvent);
                        finish();
                    }
                })
                .positiveText("确定")
                .show();
    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
