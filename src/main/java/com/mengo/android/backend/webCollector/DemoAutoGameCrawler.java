package com.mengo.android.backend.webCollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;
import cn.edu.hfut.dmic.webcollector.util.MD5Utils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

/**
 * 继承广度爬虫（breadth crawler 是最常用的爬取器直以）
 */
public class DemoAutoGameCrawler extends BreadthCrawler {
    File baseDir = new File("images");

    public DemoAutoGameCrawler(String crawlPath) {
        super(crawlPath, true);

        getConf().setAutoDetectImg(true);//只有在autoParse和autoDetecImg都为true的情况下，爬虫才会自动解析图片链接
        /**
         *设置爬取的网站地址
         * addSeed表示添加种子
         * 种子链接会在爬虫启动之前加入到爬取信息中并标记为未抓取状态，这个过程称为注入
         */
//        this.addSeedAndReturn("https://www.2dfan.com/subjects/").type("list");
//        /**
//         * 循环添加4个种子，其实就是分页，结果类似：
//         * https://www.2dfan.com/subjects/page/2
//         * https://www.2dfan.com/subjects/page/3
//         * https://www.2dfan.com/subjects/page/4
//         * https://www.2dfan.com/subjects/page/5
//         */
//        for (int pageIndex = 2; pageIndex <= 5; pageIndex++) {
//            String seedUrl = String.format("https://www.2dfan.com/subjects/page/%d", pageIndex);
//            this.addSeed(seedUrl, "list");
//        }
        /**
         * addRegex 参数为一个url正则表达式，可以用于过滤不必抓取的链接，比如.js .jpg .css……等
         * 也可以指定抓取某些规则的链接，
         */
//        this.addRegex("https://www.2dfan.com/subjects/page/.*");
        addSeed("https://www.2dfan.com/subjects");
        addRegex("https://www.2dfan.com/subjects/page/^[2-5]$");
        addRegex("https://img.tdgujian.com/.*");
        addRegex("-.*#.*");
        addRegex("-.*\\?.*");
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
//        String url = page.url();
        String contentType = page.contentType();
        if (contentType != null && contentType.startsWith("image")) {
            String extensionName = contentType.split("/")[1];
            try {
                byte[] image = page.content();
                String fileName = String.format("%s.%s", MD5Utils.md5(image), extensionName);
                File imageFile = new File(baseDir, fileName);
                FileUtils.write(imageFile, image);
                System.out.println("保存图片" + page.url() + "到" + imageFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        /**
//         * 如果此页面地址 确实是要求爬取的网址，则进行取值
//         */
//        if (page.matchType("list")) {
//            System.out.println("::::::::" + url);
//            Elements elements = page.select("h4[class=media-heading]");
//            for (Element e : elements) {
//                System.out.println(e.text());
//            }
//        }
    }

    public static void main(String[] args) throws Exception {
        /**
         * 构造器中进行数据初始化，这2个参数会传递给父类
         * crawlPath:表示设置保存爬虫记录的文件夹
         */
        DemoAutoGameCrawler crawler = new DemoAutoGameCrawler("crawl2");
        crawler.setRequester(new OkHttpRequester());
        //设置为断点爬虫，否则每次开启爬虫会重新爬取
        crawler.setResumable(true);
//        crawler.getConf().setExecuteInterval(5000);//每个线程的执行间隔时间
        crawler.start(3);

    }
}
