package com.example.svanegas.training.controller.person;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.svanegas.training.databinding.PersonItemBinding;
import com.example.svanegas.training.model.Person;

/**
 * Created by svanegas on 3/16/16.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    public PersonAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PersonItemBinding binding = PersonItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(PersonAdapter.ViewHolder holder, int position) {
        Person person = new Person();
        String names [] = { "Santiago", "Juan", "Mike", "Chris", "Franklin", "Vanegas", "Clinton" };
        person.setFirstName(names[position % names.length]);
        person.setLastName(names[Math.abs(names.length - position) % names.length]);
        person.setAge(position + (int) (Math.random() * 30));
        person.setPremium(Math.random() < 0.5);
        PersonItemBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setPerson(person);
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
