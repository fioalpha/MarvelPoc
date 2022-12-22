#! /usr/bin/env bash

set -e

dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "${dir%/*}"

readonly cyan_color="\033[1;36m"
readonly read_color="\033[0;31m"
readonly green_color="\033[0;32m"
readonly normal_color="\033[0m"

readonly cache_directory="$HOME/.marvel-project/cache"

readonly detekt_home="$cache_directory/detekt"
readonly detekt_save="$detekt_home/detekt-1.22.0"
readonly detekt_bin="$detekt_save/detekt-cli-1.22.0/bin/detekt-cli"

readonly ktlint_home="$cache_directory/ktlint"
readonly ktlint_bin="$ktlint_home/ktlint"

run_detekt() {
  if ! test -f "$detekt_bin"; then
      echo -e "${cyan_color}Installing Detekt binary${normal_color}"
      mkdir -p "$detekt_home"

      local detekt_zip="$detekt_home/detekt.zip"
      rm -rf "$detekt_zip"
      echo -e "${cyan_color}Downloading Detekt binary....${normal_color}"
      curl -sSLO https://github.com/detekt/detekt/releases/download/v1.22.0/detekt-cli-1.22.0.zip -o "$detekt_zip"
      unzip "$detekt_zip" -d "$detekt_save"

      chmod +x "$detekt_bin"
  fi
  echo -e "${cyan_color}Running Detekt ${normal_color}"
#    "$detekt_bin" --help

  "$detekt_bin" -c .config/detekt.yaml --build-upon-default-config -r xml:./app/build/reports/detekt/detekt.xml
  echo "$? skldhfjklhfklhsdklfhsdkl"
  echo ""
  if test "$?" == 1; then
    echo "asdklhjfklsdf"
    exit 1
  fi
  echo -e "${green_color}No issues found with Detekt${normal_color}"
}

run_ktlint() {
if ! test -f "$ktlint_bin";then
  echo -e "${cyan_color}Installing Ktlint${normal_color}"
  mkdir -p "$ktlint_home"
  echo -e "${cyan_color}Downloading Ktlint binary ...."
  curl -sSL https://github.com/pinterest/ktlint/releases/download/0.47.1/ktlint -o "$ktlint_bin"
      echo -e "${cyan_color}Finished Ktlint binary ...."
  chmod +x "$ktlint_bin"
fi
echo -e "${cyan_color}Running Ktlint${normal_color}"
"$ktlint_bin" --android
echo $?
echo -e "${green_color}No issue found with Ktlint"
}

readonly pmd_home="$cache_directory/pmd"
readonly pmd_bin="$pmd_home/unziped/pmd-bin-6.52.0/bin/run.sh"

run_pmd() {
if ! test -f "$pmd_bin";then
  echo -e "${cyan_color}Install PMD binary${normal_color}"
  mkdir -p "$pmd_home"

  local zip_file_path="$pmd_home/pmd_6.zip"
  curl -sSL https://github.com/pmd/pmd/releases/download/pmd_releases%2F6.52.0/pmd-bin-6.52.0.zip -o"$zip_file_path"
  unzip "$zip_file_path" -d "$pmd_home/unziped"
fi
  "$pmd_bin" pmd -R .config/pmd.xml -d ./src/main/java
}

#./gradlew clean
#run_pmd
run_detekt
#run_ktlint