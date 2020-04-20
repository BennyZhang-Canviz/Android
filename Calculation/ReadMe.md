Android Kotlin Jetpack demo.
演示如何使用View Model和数据绑定，以及Fragment之间切换如果共享ViewModel的数据。
ViewModel要在activity中实例化，在Fragment中只需要引用activity中的ViewModel即可。
另外一个示例： https://github.com/shuza/Shared-ViewModel-sample

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