package com.tech42labs.alertme.dagger;

import android.content.Context;

import com.tech42labs.alertme.application.AlertMeApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mari on 3/10/17.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent  {

    void inject(AlertMeApplication application);

    void inject(Context context);
}
