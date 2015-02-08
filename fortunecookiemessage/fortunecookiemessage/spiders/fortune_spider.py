import scrapy
from bs4 import BeautifulSoup

class FortuneSpider(scrapy.Spider):
    name = "fortunecookiemessage"
    allowed_domains = ["fortunecookiemessage.com"]
    start_urls = ["http://www.fortunecookiemessage.com/archive.php?start={0}".format(i)
                  for i in range(0, 801, 50)]

    def parse(self, response):
        soup = BeautifulSoup(response.body)
        with open('fortunecookiemessage.com-fortunes', 'a') as f:
            for fortune in soup.table.find_all('a'):
                text = fortune.string.encode('utf-8')
                if text != 'Fortune Cookie Quotes: ':
                    f.write(fortune.string.encode('utf-8'))
                    f.write("\n%\n")
