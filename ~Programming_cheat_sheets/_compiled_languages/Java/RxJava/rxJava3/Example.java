package com.example;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.reactivestreams.Publisher;

public class Example {
  public static void main(String[] args) {


    // https://github.com/ReactiveX/RxJava/wiki/How-To-Use-RxJava

    // https://github.com/ReactiveX/RxJava/wiki/Backpressure-(2.0)


    Flowable.range(1, 1000)
            .observeOn(Schedulers.computation()) // Diff thread
            .subscribe(v -> {
              System.out.println(v);
            }, Throwable::printStackTrace);




    //
    //=============================== Flowable
    //


    Flowable
            .fromArray(new String[]{"aaa", "bbb", "ccc"})
            .flatMap(w -> {
              return Flowable.just(w);
            })
            .map(w -> {
              return w.toUpperCase();
//              throw new RuntimeException("Flowable-aaa__");
            })

//            .doOnError(e -> { // executes then throws the error <======<<<<
//              System.out.println("Flowable-doOnError-e: " + e);
//            })
//            .doOnNext(n -> {
//              System.out.println("Flowable-doOnNext-n: " + n);
//
//            })
//            .doOnComplete(() -> {
//              System.out.println("Flowable-doOnComplete-COMPLETE!");
//
//            })
            .observeOn(Schedulers.computation())
            .subscribe(n -> {
              System.out.println("Flowable-n: " + n);

            }, e -> {
              System.out.println("Flowable-e: " + e);
            }, () -> {
              System.out.println("Flowable-COMPLETE!\n\n\n");

            });


    //
    //=============================== Single
    //

    Single.just("aaa")
            .map(w -> {
              return w.toUpperCase();
            })
//            .doOnError(e -> { // executes then throws the error <======<<<<
//              System.out.println("Single-doOnError-e: " + e);
//            })
//            .doOnSuccess(s -> {
//              System.out.println("Single-doOnSuccess-s: " + s);
//            })
//            .doOnSubscribe(sb -> {
//              System.out.println("Single-doOnSubscribe-sb: " + sb.getClass().getName());
//            })
            .observeOn(Schedulers.computation())
            .subscribe(s -> {
              System.out.println("Single-s: " + s);
            }, e -> {
              System.out.println("Single-e: " + e);
            });


    //
    //
    //


//    Flowable.range(1, 10)
//            .observeOn(Schedulers.computation())
//            .subscribe(v -> {
//              System.out.println(v);
//            }, Throwable::printStackTrace);
//
//    try {
//      Thread.sleep(10_000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }

  }
}
