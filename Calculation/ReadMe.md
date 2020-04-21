Android Kotlin Jetpack demo.
演示如何使用View Model和数据绑定，以及Fragment之间切换如果共享ViewModel的数据。
ViewModel要在activity中实例化，在Fragment中只需要引用activity中的ViewModel即可。
另外一个示例： https://github.com/shuza/Shared-ViewModel-sample

//ViewModelProvider(this)[ScoreViewModel::class.java] 这一句中的this决定了view model的作用范围。比如在Fragement中this代表当前fragement, 但是如果把this换成它的父级，则该view model的作用域就成了activity.

1. Activity中引用view model:
    class MainActivity : AppCompatActivity() {
        val scoreViewMode: ScoreViewModel by lazy {
            ViewModelProvider(this)[ScoreViewModel::class.java]
        }
        ....

2. Fragemnet中调用activity中的view model
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //以下是调用activity中的view model
        activity?.let{
            scoreViewMode = ViewModelProvider(it)[ScoreViewModel::class.java]
        }
