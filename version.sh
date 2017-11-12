#!/bin/bash
ver='$(git describe 2>/dev/null)';if [[ ${ver:0:1} == 'r' ]]; then echo $ver; else echo 'no aplica'; fi;
