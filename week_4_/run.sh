#!/bin/bash
clear
python3 reformat_nba_data.py
python3 produce_tables.py
python3 analysis.py
python3 cblhelper.py
cobc -x analysis.cbl -o testRun
chmod +x testRun
./testRun
