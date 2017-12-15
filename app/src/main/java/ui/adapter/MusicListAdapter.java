package ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import app.MyApplication;
import rmj.example.com.rmj_common_project.R;
import service.entity.Audio;
import utils.FormatHelper;
import utils.ImageUtil;


/**
 * 本地音乐列表适配器
 * Created by Idea on 2016/5/26.
 */
public class MusicListAdapter extends BaseAdapter {
    private static final String TAG = "RhymeMusic";
    private static final String SUB = "[MusicListAdapter]#";
    private Typeface iconfont;
    private Context context;

    private ArrayList<Audio> audioList;


    public MusicListAdapter(Context context, ArrayList<Audio> audioList) {
        this.context = context;
        this.audioList = audioList;
        iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont/iconfont.ttf");
    }

    @Override
    public int getCount() {
        return audioList.size();
    }

    @Override
    public Object getItem(int position) {
        return audioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_music, null);
            viewHolder = new ViewHolder();

            viewHolder.musicIndex = (TextView) convertView.findViewById(R.id.audio_index);
            viewHolder.musicTitle = (TextView) convertView.findViewById(R.id.music_title);
            viewHolder.musicArtist = (TextView) convertView.findViewById(R.id.music_artist);
            viewHolder.musicDuration = (TextView) convertView.findViewById(R.id.music_duration);
            viewHolder.musicCover = (ImageView) convertView.findViewById(R.id.music_cover);
            viewHolder.check_icon=(TextView) convertView.findViewById(R.id.check_icon);
            viewHolder.bg=(RelativeLayout) convertView.findViewById(R.id.bg);
            convertView.setTag(viewHolder);

            //MyApplication.getInstance().setTextAudioIndex(viewHolder.musicIndex);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Log.i("has_updated","更新了");
        Audio audio = audioList.get(position);
        viewHolder.musicIndex.setText(FormatHelper.formatIndex(position + 1));
        viewHolder.musicTitle.setText(audio.getTitle());
        if (audio.getArtist().equals("<unknown>")){
            viewHolder.musicArtist.setText("未知");
        }else{
            viewHolder.musicArtist.setText(audio.getArtist());
        }
       // viewHolder.musicArtist.setText(audio.getArtist());
        viewHolder.musicDuration.setText(FormatHelper.formatDuration(audio.getDuration()));
        viewHolder.check_icon.setTypeface(iconfont);
        Bitmap bitmap = ImageUtil.getAlbumCover(context, audio.getId());

        if (bitmap == null) {
            viewHolder.musicCover.setImageResource(R.mipmap.transparent);
        } else {
            viewHolder.musicCover.setImageBitmap(bitmap);
        }
        int selectPosition=MyApplication.getInstance().getCurrentMusic();
        if (selectPosition==position){
            viewHolder.bg.setBackgroundColor(context.getResources().getColor(R.color.bottom_bg));
        }else{
            viewHolder.bg.setBackgroundColor(context.getResources().getColor(R.color.list_bg));
        }

        return convertView;
    }


    /**
     * 创建内部类ViewHolder，用于对控件的实例进行缓存。
     */
    class ViewHolder {
        TextView musicIndex;
        TextView musicTitle;
        TextView musicArtist;
        TextView musicDuration;
        ImageView musicCover;
        TextView check_icon;
        RelativeLayout bg;
    }
}
