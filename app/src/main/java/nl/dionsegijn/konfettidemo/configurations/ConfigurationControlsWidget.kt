package nl.dionsegijn.konfettidemo.configurations

import android.content.Context
import android.os.Build
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.bottomsheet_config_controls.view.*
import nl.dionsegijn.konfettidemo.R
import nl.dionsegijn.konfettidemo.configurations.selection_views.ColorSelectionView
import nl.dionsegijn.konfettidemo.configurations.selection_views.MultiSeekbarSelectionView
import nl.dionsegijn.konfettidemo.configurations.selection_views.SeekbarSelectionView
import nl.dionsegijn.konfettidemo.configurations.selection_views.ShapeSelectionView
import nl.dionsegijn.konfettidemo.configurations.settings.Configuration
import nl.dionsegijn.konfettidemo.configurations.viewpager.ConfigPagerAdapter
import nl.dionsegijn.konfettidemo.configurations.viewpager.TabConfig
import nl.dionsegijn.konfettidemo.interfaces.OnConfigurationChangedListener

/**
 * Created by dionsegijn on 5/21/17.
 */
class ConfigurationControlsWidget : LinearLayout, OnConfigurationChangedListener {

    val configuration = Configurations()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inflate(context, R.layout.bottomsheet_config_controls, this)
        orientation = VERTICAL

        viewPager.adapter = ConfigPagerAdapter(getTabs())
        tabLayout.setupWithViewPager(viewPager)
        for (i in 0..tabLayout.tabCount - 1) {
            tabLayout.getTabAt(i)?.setIcon(getTabs()[i].icon)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
            elevation = 100f
        }
    }

    override fun onConfigurationChanged(configuration: Configuration) {

    }

    fun setOnTabSelectedListener(onTabSelectedListener: TabLayout.OnTabSelectedListener) {
        tabLayout.addOnTabSelectedListener(onTabSelectedListener)
    }

    fun getTabs(): Array<TabConfig> {
        return arrayOf(
                TabConfig(R.drawable.ic_paint, ColorSelectionView(context)),
                TabConfig(R.drawable.ic_shapes, ShapeSelectionView(context)),
                TabConfig(R.drawable.ic_speed, MultiSeekbarSelectionView(context, "Speed", 1, 10, 1, 10)),
                TabConfig(R.drawable.ic_time_to_live, SeekbarSelectionView(context, configuration.active,  "Time to live", 500, 5000, 1000)))
    }

}
