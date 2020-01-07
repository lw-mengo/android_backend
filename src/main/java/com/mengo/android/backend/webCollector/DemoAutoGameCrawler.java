package com.mengo.android.backend.webCollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 继承广度爬虫（breadth crawler 是最常用的爬取器直以）
 */
public class DemoAutoGameCrawler extends BreadthCrawler {
    public DemoAutoGameCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        /**
         *设置爬取的网站地址
         * addSeed表示添加种子
         * 种子链接会在爬虫启动之前加入到爬取信息中并标记为未抓取状态，这个过程称为注入
         */
        this.addSeedAndReturn("https://www.2dfan.com/subjects/").type("list");
        /**
         * 循环添加4个种子，其实就是分页，结果类似：
         * https://www.2dfan.com/subjects/page/2
         * https://www.2dfan.com/subjects/page/3
         * https://www.2dfan.com/subjects/page/4
         * https://www.2dfan.com/subjects/page/5
         */
        for (int pageIndex = 2; pageIndex <= 5; pageIndex++) {
            String seedUrl = String.format("https://www.2dfan.com/subjects/page/%d", pageIndex);
            this.addSeed(seedUrl,"list");
        }
        /**
         * addRegex 参数为一个url正则表达式，可以用于过滤不必抓取的链接，比如.js .jpg .css……等
         * 也可以指定抓取某些规则的链接，
         */
//        this.addRegex("https://www.2dfan.com/subjects/page/.*");

        /**
         * 设置线程数
         */
        setThreads(20);
        getConf().setTopN(40);
    }

    /**
     * 必须重写visit 方法，作用是：
     * 在整个抓取过程中，只要抓到符合要求的页面，webCollector就会回调该方法，并传入一个包含了页面所有信息的page对象
     *
     * @param page
     * @param crawlDatums
     */
    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        String url = page.url();
        /**
         * 如果此页面地址 确实是要求爬取的网址，则进行取值
         */
        if (page.matchType("list")) {
            System.out.println("::::::::" + url);
            Elements elements = page.select("h4[class=media-heading]");
            for (Element e : elements) {
                System.out.println(e.text());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        /**
         * 构造器中进行数据初始化，这2个参数会传递给父类
         * crawlPath:表示设置保存爬虫记录的文件夹
         */
        DemoAutoGameCrawler crawler = new DemoAutoGameCrawler("crawl", false);
        crawler.getConf().setExecuteInterval(5000);
        crawler.start(1);

    }
}
