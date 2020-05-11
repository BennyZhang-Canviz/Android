package com.example.shopping

import android.app.Application
import com.example.shopping.data.ShoppingDatabase
import com.example.shopping.repositories.ShoppingRepository
import com.example.shopping.viewmodel.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

//此处没有用到，待研究。
//参考资料： https://www.jianshu.com/p/9588aa1f082f 和 https://github.com/androiddevs18/MVVMShoppingList

class BaseApplication: Application(), KodeinAware {
    // 在一个Android项目中，很多依赖都需要保持单例，这样能够保证合理的资源规划，
    // 比如，Retrofit的实例化，比如Gson对象的实例化，这里我们直接在Application中进行配置：
    //KodeinAware是一个接口，它意味着，实现该接口的对象都会持有一个Kodein容器：
    //MyApplication 实现了KodeinAware接口，并实例化了一个Kodein容器，
    // 接下来我们要做的，就是把对应的依赖装进Kodein容器中。
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@BaseApplication))//导入预设的 android 组件
        bind() from singleton { ShoppingRepository(instance()) }
        //bind() from singleton { ShoppingDatabase(instance()) }
        bind() from provider { ShoppingViewModelFactory(instance()) }
    }

}

