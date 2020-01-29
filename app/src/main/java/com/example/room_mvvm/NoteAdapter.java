package com.example.room_mvvm;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.TextView;import androidx.annotation.NonNull;import androidx.recyclerview.widget.DiffUtil;import androidx.recyclerview.widget.ListAdapter;import androidx.recyclerview.widget.RecyclerView;public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {	onItemClickListenerForNote listener;	public NoteAdapter() {		super(DIFF_CALLBACK);	}	private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {		@Override		public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {			return oldItem.getId() == newItem.getId();		}		@Override		public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {			return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDescription().equals(newItem.getDescription()) && oldItem.getPriority() == newItem.getPriority();		}	};	@NonNull	@Override	public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, true);		return new NoteHolder(itemView);	}	@Override	public void onBindViewHolder(@NonNull NoteHolder holder, int position) {		Note currentNote = getItem(position);		holder.title.setText(currentNote.getTitle());		holder.priority.setText(currentNote.getPriority() + "");		holder.description.setText(currentNote.getDescription());	}	public Note getNoteAt(int position) {		return getItem(position);	}	class NoteHolder extends RecyclerView.ViewHolder {		private TextView title, description, priority;		public NoteHolder(@NonNull View itemView) {			super(itemView);			title = itemView.findViewById(R.id.textview_title);			description = itemView.findViewById(R.id.description);			priority = itemView.findViewById(R.id.text_view_priority);			itemView.setOnClickListener(v -> {				if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {					listener.onNoteClicked(getItem(getAdapterPosition()));				}			});		}	}	public interface onItemClickListenerForNote {		void onNoteClicked(Note note);	}	public void setNoteItemClickListener(onItemClickListenerForNote listener) {		this.listener = listener;	}}