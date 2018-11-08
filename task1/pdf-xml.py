import os
import requests
url="http://localhost:8070/api/processFulltextDocument"
loser=[]
#将oriPDFs中的所有文件名存入列表中
# -*- coding: utf-8 -*-
for root, dirs, files in os.walk("F:\\CUFE中央财经大学\\大三上\\信息组织与检索\\project1\\oriPDFs"):
    for pdfname in files:
        pdfname1 = "F:\\CUFE中央财经大学\\大三上\\信息组织与检索\\project1\\oriPDFs\\" + pdfname
        params = dict(input=open(pdfname1, 'rb'))
        try:
            tei = requests.post(url, files=params,timeout=30)
            filename=pdfname+".xml"
            file = open(filename, "w",encoding='utf-8')
            file.write(tei.text)
        except Exception as e:
            loser.append(pdfname)
            continue
for root, dirs, files in os.walk("D:\\pythonwork\\IRproject1"):
    print(len(files))
print(loser)



