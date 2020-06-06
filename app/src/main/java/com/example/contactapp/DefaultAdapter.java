package com.example.contactapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ktpackage.*;

public class DefaultAdapter extends ArrayAdapter<ContactModel> implements View.OnClickListener{
    private Activity mActivity;
    private ContactData myContacts;

    public DefaultAdapter(Activity activity, ContactData myContacts) {
        super(activity.getApplicationContext(), R.layout.row_item, myContacts.getAll());
        this.myContacts = myContacts;
        this.mActivity = activity;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        final ContactModel contact=(ContactModel)object;

        switch (v.getId())
        {
            case R.id.action_delete:
                new AlertDialog.Builder(mActivity)
                        .setTitle("Confirmation")
                        .setMessage("Delete record?")
                        .setIcon(R.drawable.ic_dialog_alert)
                        .setPositiveButton(R.string.yes,
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        myContacts.delete(contact);
                                        notifyDataSetChanged();
                                        dialog.dismiss();

                                    }
                                })
                        .setNegativeButton(R.string.cancel,
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();

                break;
        }
    }

    private static class ViewHolder {
        TextView firstName;
        TextView lastName;
        ImageView delete;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactModel contact = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.firstName = (TextView) convertView.findViewById(R.id.first_name);
            viewHolder.lastName = (TextView) convertView.findViewById(R.id.last_name);
            viewHolder.delete = (ImageView) convertView.findViewById(R.id.action_delete);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.firstName.setText(contact.getFirstName());
        viewHolder.lastName.setText(contact.getLastName());
        viewHolder.delete.setOnClickListener(this);
        viewHolder.delete.setTag(position);

        return convertView;
    }
}
