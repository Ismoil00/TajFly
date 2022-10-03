package com.example.mydiplomaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class AdapterFlyingPlanes extends RecyclerView.Adapter<AdapterFlyingPlanes.ViewHolder> {

    FlightsPage new_activity;
    JSONObject object;

    //Выбираем вёрстку одного элемента списка
    @Override
    public AdapterFlyingPlanes.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int index) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.template_flights, viewGroup, false);
        return new ViewHolder(view);
    }

    //Указываем количество элементов в списке
    @Override
    public int getItemCount() {
        return new_activity.array.length();

    }

    //Привязываем переменные к объектам на форме
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView companyName, flightNumber, airportFrom, airportTo, cityFlyingFrom, cityFlyingTo, departureTime, arrivalTime, status, flyingHours, stops;
        ImageView planesImages, flagFrom, flagTo;

        public ViewHolder(View view) {
            super(view);
            companyName = view.findViewById(R.id.companyName);
            flightNumber = view.findViewById(R.id.flightNumber);
            airportFrom = view.findViewById(R.id.airportFrom);
            airportTo = view.findViewById(R.id.airportTo);
            cityFlyingFrom = view.findViewById(R.id.cityFlyingFrom);
            cityFlyingTo = view.findViewById(R.id.cityFlyingTo);
            departureTime = view.findViewById(R.id.departureTime);
            arrivalTime = view.findViewById(R.id.arrivalTime);
            flyingHours = view.findViewById(R.id.flyingHours);
            status = view.findViewById(R.id.status);
            stops = view.findViewById(R.id.stops);
            planesImages = view.findViewById(R.id.planesImages);
            flagFrom = view.findViewById(R.id.flagFrom);
            flagTo = view.findViewById(R.id.flagTo);
        }
    }

    //Заполнение полей элемента списка при отображении (что куда записывать)
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int index) {

        try {
            object = new_activity.array.getJSONObject(index);
            viewHolder.companyName.setText(object.getString("company_name"));
            viewHolder.flightNumber.setText(object.getString("flight_number"));
            viewHolder.airportFrom.setText(object.getString("airport_from"));
            viewHolder.airportTo.setText(object.getString("airport_to"));
            viewHolder.cityFlyingFrom.setText(object.getString("city_flying_from"));
            viewHolder.cityFlyingTo.setText(object.getString("city_flying_to"));
            viewHolder.departureTime.setText(object.getString("departure_time"));
            viewHolder.arrivalTime.setText(object.getString("arrival_time"));
            viewHolder.flyingHours.setText(object.getString("flying_hours"));
            viewHolder.status.setText(object.getString("real_time"));
            viewHolder.stops.setText(object.getString("stops"));

            Glide.with(new_activity).load(object.getString("image_link")).into(viewHolder.planesImages);
            //Glide.with(activity).load(object.getString("flag_from")).into(viewHolder.flagFrom);
            //Glide.with(activity).load(object.getString("flag_to")).into(viewHolder.flagTo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}