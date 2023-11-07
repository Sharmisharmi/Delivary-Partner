plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
//    id ("com.google.gms.google-services")
    id ("kotlin-android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.elitedelivery"
    compileSdk = 33
    dataBinding{
        isEnabled=true
    }
    lintOptions {
        // Turns off checks for the issue IDs you specify.
        disable ("TypographyFractions","TypographyQuotes")
        // Turns on checks for the issue IDs you specify. These checks are in
        // addition to the default lint checks.
        enable ("RtlHardcoded","RtlCompat", "RtlEnabled")
        // To enable checks for only a subset of issue IDs and ignore all others,
        // list the issue IDs with the 'check' property instead. This property overrides
        // any issue IDs you enable or disable using the properties above.
        checkOnly ("NewApi", "InlinedApi")
        // If set to true, turns off analysis progress reporting by lint.
        isQuiet =true
        // if set to true (default), stops the build if errors are found.
        isAbortOnError =false
        // if true, only report errors.
        isIgnoreWarnings =true
    }
    defaultConfig {
        applicationId = "com.example.elitedelivery"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled= true
        resConfigs ("en")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding= true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.android.volley:volley:1.2.1")
//    implementation("com.google.firebase:firebase-auth:22.2.0")
    implementation("com.google.firebase:firebase-messaging:23.3.1")
    implementation("androidx.test:core-ktx:1.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //kotlin code
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.6.0")
    implementation ("com.google.android.gms:play-services-places:16.0.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.2.2")
    implementation ("com.squareup.okhttp3:okhttp:4.2.2")
    implementation ("com.google.code.gson:gson:2.8.6")
    implementation ("com.squareup.retrofit2:retrofit:2.6.2")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.2")
    implementation ("com.squareup.retrofit2:converter-scalars:2.6.2")

    // Google map
    implementation ("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("com.google.maps.android:android-maps-utils:0.5")
    implementation ("com.google.android.libraries.places:places:2.4.0")


    // Firebase
    implementation ("com.google.firebase:firebase-auth:20.0.0")
    implementation (platform("com.google.firebase:firebase-bom:30.3.1"))
    implementation ("com.google.firebase:firebase-analytics")
    implementation ("androidx.work:work-runtime:2.8.1")
    implementation ("com.google.firebase:firebase-messaging:21.0.1")
    implementation ("com.google.firebase:firebase-database:16.0.1")
//    implementation("com.google.firebase:firebase-analytics-ktx")
//    implementation(platform("com.google.firebase:firebase-bom:32.5.0"))


    implementation ("com.google.android.gms:play-services-auth:20.7.0")                             //Phone Number hint picker
    implementation ("com.google.zxing:core:3.3.3")                                                  // Payment


    implementation ("com.github.GoodieBag:Pinview:v1.4")                                            // OtpView
    implementation ("de.hdodenhof:circleimageview:3.0.1")                                           // Circle ImageView
    implementation ("com.squareup.picasso:picasso:2.8")                                             // Picasso
    implementation ("com.github.bumptech.glide:glide:4.9.0")                                        //Glide
    implementation ("com.jaredrummler:material-spinner:1.3.1")                                      // Material Spinner
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.22")                             // gif


    implementation ("com.github.sparrow007:carouselrecyclerview:1.2.6")                             // 3d CarouseView
    implementation ("nl.joery.animatedbottombar:library:1.1.0")                                     // bottom navigationView

    implementation("com.github.lzyzsd:circleprogress:1.1.1")
    implementation ("com.github.prabhat1707:EasyWayLocation:2.4")

    implementation ("com.github.ChengangFeng:TickView:v1.0.2")                                      // tickview github animation
    implementation ("nl.dionsegijn:konfetti:1.3.2")                                                 // Confetti
    implementation ("com.github.NitishGadangi:TypeWriter-TextView:v1.3")                            // TypeWriting Text
    implementation ("com.github.MAXDeliveryNG:slideview:1.1.0")                                     //Swipe Button

    implementation ("com.facebook.shimmer:shimmer:0.5.0")
}