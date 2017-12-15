package ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rmj.example.com.rmj_common_project.R;
import service.entity.News;
import utils.ImageUtil;

/**
 * Created by admin on 2017/11/25.
 */

public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    private News myNews;

    public NewsAdapter(Context context, News news) {
        this.mContext = context;
        this.myNews = news;
    }

    @Override
    public int getCount() {
        return myNews.getResult().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return myNews.getResult().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.fragment_top_item,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        String title=myNews.getResult().getData().get(position).getTitle();
        if (!TextUtils.isEmpty(title)){
            viewHolder.title.setText(title);
        }
        String url1=myNews.getResult().getData().get(position).getThumbnail_pic_s();
        String url2=myNews.getResult().getData().get(position).getThumbnail_pic_s02();
        String url3=myNews.getResult().getData().get(position).getThumbnail_pic_s03();
        if (!TextUtils.isEmpty(url1)){
            //GlideUtils.getInstance().loadBitmap(mContext,url1,viewHolder.img1);
            ImageUtil.loadImage(url1,viewHolder.img1);
        }
        if (!TextUtils.isEmpty(url2)){
            //GlideUtils.getInstance().loadBitmap(mContext,url2,viewHolder.img2);
            ImageUtil.loadImage(url2,viewHolder.img2);
        }
        if (!TextUtils.isEmpty(url3)){
            //GlideUtils.getInstance().loadBitmap(mContext,url3,viewHolder.img3);
            ImageUtil.loadImage(url3,viewHolder.img3);
        }



//        ImageUtil.loadImage(url1,viewHolder.img1);
//        ImageUtil.loadImage(url2,viewHolder.img2);
//        ImageUtil.loadImage(url3,viewHolder.img3);

        return convertView;
    }

     class ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.img1)
        ImageView img1;
        @BindView(R.id.img2)
        ImageView img2;
        @BindView(R.id.img3)
        ImageView img3;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
