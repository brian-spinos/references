#!/bin/bash

# $ chmod +x brian.sh
# ./brian.sh

#
# Utility to add two numbers
# $ ./brian.sh -f 10 -b 20 #=> 30
#

argc=$# # number of arguments
argv=("$@")

main(){
  ARG_FOO=""
  ARG_BAR=""

  # needs to be double braces
  for((i=0;i<argc;i++)); do
      item="${argv[i]}"

      if [[ "${item}" = "--foo" || "${item}" = "-f" ]]; then
        ARG_FOO="${argv[i+1]}"
      fi

      if [[ "${item}" = "--bar" || "${item}" = "-b" ]]; then
        ARG_BAR="${argv[i+1]}"
      fi
  done

  echo $((ARG_FOO + ARG_BAR))
}

#
# Run main function
#
main
