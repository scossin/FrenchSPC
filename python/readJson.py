from typing import Dict, List

import pydantic
from model.RCP import RCPHTML, RCPPDF


def testRCPHTML():
    rcps: List[RCPHTML] = pydantic.parse_file_as(List[RCPHTML], "./html_spc.json")
    one_rcp: List[RCPHTML] = list(filter(lambda x: x.cis == '61356349', rcps))
    print(one_rcp[0].RcpIndicTherap)

def testRCPPDF():
    rcps: List[RCPPDF] = pydantic.parse_file_as(List[RCPPDF], "./pdf_spc.json")
    one_rcp: List[RCPPDF] = list(filter(lambda x: x.numCode == '149859', rcps))
    print(one_rcp[0].RcpIndicTherap)

if __name__ == "__main__":
    testRCPPDF()
