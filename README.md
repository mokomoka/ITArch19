# ITArch19
未来大のITアーキテクチャ特論の課題です。  
内容は以下の通り。  
___
## 演習：オリジナルなAndroidサービスを作る
以下の条件を満たすサービスを実装する
- アプリ外からアクセス可とすること
  - AIDLを使うことが目的
- 実装するサービスが提供する機能は、各自でオリジナルなものを考えること
  - 昨年度までの履修者作成のものと被らない機能を考案
  - サービスとしての有効性は問わない
- エミュレータで動作すること
- サービス機能を呼び出す側のアプリも作成すること
___
## 実装したもの：適当な色を表示できるアプリ
### 概要

<img src="https://user-images.githubusercontent.com/27045715/70064927-aa04c480-162d-11ea-9575-3bc6bc7674b4.png" width="300px">

”GET COLOR” ボタンをタップすると…

<img src="https://user-images.githubusercontent.com/27045715/70064928-aa9d5b00-162d-11ea-9c12-44fd5930a764.png" width="300px">

その都度、背景色がランダムなカラーに変わり、そのカラーのカラーコードが "Color Code" 欄に表示される。  
おまけとして

<img src="https://user-images.githubusercontent.com/27045715/70064929-aa9d5b00-162d-11ea-81ce-033e856d5ceb.png" width="300px">

カラーコードのテキストをタップすると、クリップボードにコピーできる。なにかに使えるかもしれない。

### 実装について
- RemoteServiceApp
  - Service実装してあって、そこでRGBそれぞれの値を0～255の間でランダムに選んでいる
  - MainActivityは初期と同じTextView表示してるだけ
- ClientApp（AIDL使ってRemoteServiceAppのServiceにアクセスしているアプリ）
  - Serviceから返ってきたカラーを背景色に設定
  - カラーを16進数に変換して（カラーコードとして）表示
  - カラーコードをコピーできるようClipboard Managerも使っている
  
### 間に合わなかったところ
- 背景をいっぺんにﾊﾟｯって変えるのではなく、円が広がるように変えたりしたかった
  - 透明度変化で徐々に変わる…でも良かったかも
- 背景の色によってカラーコードを表示する文字の色も変えたかった
  - 現状、背景色が暗いと文字が見づらい
  - イメージはこのサイト [Palettable](https://www.palettable.io/)
  - どうやって適切な色を決めるのか…

### 主な参考サイト
- [Android インターフェース定義言語（AIDL）  | Android Developers](https://developer.android.com/guide/components/aidl)
- [AndroidでServiceと通信する(Kotlinサンプル) - Qiita](https://qiita.com/satken2/items/49dd76d848ceb208e937)
- 去年以前にこの課題をやっている先人のRepositoryをいくつか
- [【Android】TextViewにonClickイベントを付与する - MogLog](https://sandragon.hatenablog.com/entry/2013/07/09/014639)
