package hue.edu.sim.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import hue.edu.sim.R;
import hue.edu.sim.manager.NoticeManager;
import hue.edu.sim.model.MainPageItem;
import hue.edu.sim.model.Notice;

import java.util.List;

/**
 * 
 * ��ҳ��˵�������.
 * 
 * @author shimiso
 */
public class MainPageAdapter extends BaseAdapter {

	private Context context;

	private List<MainPageItem> list;
	private LayoutInflater mInflater;

	public MainPageAdapter(Context c) {
		super();
		this.context = c;
	}

	public void setList(List<MainPageItem> list) {
		this.list = list;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int index) {

		return list.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.main_page_item, null);
		ImageView appImage = (ImageView) convertView
				.findViewById(R.id.itemImage);
		TextView appName = (TextView) convertView.findViewById(R.id.itemText);

		if (index <= 1) {
			TextView paopao = (TextView) convertView.findViewById(R.id.paopao);
			NoticeManager noticeManager = NoticeManager.getInstance(context);
			Integer unreadCount = 0;
			if (index == 0) {
				unreadCount = noticeManager
						.getUnReadNoticeCountByType(Notice.CHAT_MSG);
			} else if (index == 1) {
				Integer countAdd = noticeManager
						.getUnReadNoticeCountByType(Notice.ADD_FRIEND);
				Integer countSys = noticeManager
						.getUnReadNoticeCountByType(Notice.SYS_MSG);
				countAdd = (countAdd == null ? 0 : countAdd);
				countSys = (countSys == null ? 0 : countSys);
				unreadCount = countAdd + countSys;
			}

			if (unreadCount > 0) {
				paopao.setText("" + unreadCount);
				paopao.setVisibility(View.VISIBLE);
			} else {
				paopao.setVisibility(View.GONE);
			}
		}
		MainPageItem info = list.get(index);
		if (info != null) {
			appName.setText(info.getName());
			appImage.setImageResource(info.getImage());
		}
		return convertView;
	}

}