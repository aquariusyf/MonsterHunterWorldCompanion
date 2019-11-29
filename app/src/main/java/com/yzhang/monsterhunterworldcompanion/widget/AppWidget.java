package com.yzhang.monsterhunterworldcompanion.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.yzhang.monsterhunterworldcompanion.AilmentListActivity;
import com.yzhang.monsterhunterworldcompanion.ArmorSetListActivity;
import com.yzhang.monsterhunterworldcompanion.EventListActivity;
import com.yzhang.monsterhunterworldcompanion.MonsterListActivity;
import com.yzhang.monsterhunterworldcompanion.R;
import com.yzhang.monsterhunterworldcompanion.SkillListActivity;
import com.yzhang.monsterhunterworldcompanion.WeaponListActivity;

/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setOnClickPendingIntent(R.id.tv_widget_event_nav_btn, createEventListPendingIntent(context));
        views.setOnClickPendingIntent(R.id.tv_widget_monster_nav_btn, createMonsterListPendingIntent(context));
        views.setOnClickPendingIntent(R.id.tv_widget_armor_nav_btn, createArmorListPendingIntent(context));
        views.setOnClickPendingIntent(R.id.tv_widget_weapon_nav_btn, createWeaponListPendingIntent(context));
        views.setOnClickPendingIntent(R.id.tv_widget_skill_nav_btn, createSkillListPendingIntent(context));
        views.setOnClickPendingIntent(R.id.tv_widget_ailment_nav_btn, createAilmentListPendingIntent(context));
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /** Create pending intent to invoke event list activity */
    private static PendingIntent createEventListPendingIntent(Context context) {
        Intent intent = new Intent(context, EventListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
    }

    /** Create pending intent to invoke monster list activity */
    private static PendingIntent createMonsterListPendingIntent(Context context) {
        Intent intent = new Intent(context, MonsterListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
    }

    /** Create pending intent to invoke armor list activity */
    private static PendingIntent createArmorListPendingIntent(Context context) {
        Intent intent = new Intent(context, ArmorSetListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
    }

    /** Create pending intent to invoke weapon list activity */
    private static PendingIntent createWeaponListPendingIntent(Context context) {
        Intent intent = new Intent(context, WeaponListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
    }

    /** Create pending intent to invoke skill list activity */
    private static PendingIntent createSkillListPendingIntent(Context context) {
        Intent intent = new Intent(context, SkillListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
    }

    /** Create pending intent to invoke Ailment list activity */
    private static PendingIntent createAilmentListPendingIntent(Context context) {
        Intent intent = new Intent(context, AilmentListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        return pendingIntent;
    }

}

