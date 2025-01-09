package com.menasy.recviewlandbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.menasy.recviewlandbook.databinding.RecycleRowBinding;

import java.util.ArrayList;

public class LandAdapter extends RecyclerView.Adapter<LandAdapter.LandHolder>
{
    ArrayList<Land> lst;
    LandAdapter(ArrayList<Land> landList)
    {
        this.lst = landList;
    }

    // ViewHolder sınıfından bir obje oluşturulduğunda çağrılır.
    //Xml i bağlama işlemi burda yapılır.
    @Override
    public LandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate xml kodlarını java dilinde işlenir hale getirmek için nesneye çevirir
        // Ve onları işlenmeye, kullanılmaya hazır hale getirir.
        RecycleRowBinding rcb = RecycleRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return  new LandHolder(rcb);
        //.from() içindeki parametre nereye bağlanacağını soruryor aslında normalde this kullanırdık
        // Ama dışardan bize parent olarak gonderilen değere bağlanmamız lazım bu yuzden parente bağlanırız
    }

    // Layout içinde hangi verileri gosterceksek burda yapılır.
    @Override
    public void onBindViewHolder(@NonNull LandAdapter.LandHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.textRec.setText(lst.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //itemView, RecyclerView içindeki bir öğenin tamamını temsil eden, görünüm hiyerarşisinin köküdür.
            // Yani, RecyclerView'da bir öğenin içinde ne varsa (TextView, ImageView, vs.), bunların hepsinin kapsayıcısıdır.
            @Override
            public void onClick(View v) {
                //-> intentler bazı durumlarda yeteriz kalabiliyor orneğin bitmaplerde
                //-> Bu durumda singelton clasına başvurabiliriz.
                Intent intent = new Intent(holder.itemView.getContext(),DetailsActivty.class);
//                intent.putExtra("land",lst.get(position));
                Singelton sing = Singelton.getInstance();
                sing.setLand(lst.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    //Xml in kaç kere oluşacağını soyler
    public int getItemCount() {
        return lst.size();
    }

    // Adaptor layout ile kodları biribirine bağlamak ve işlemek için kullanılır.
    public class LandHolder extends RecyclerView.ViewHolder
    {
        private RecycleRowBinding binding;

        public LandHolder(RecycleRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
