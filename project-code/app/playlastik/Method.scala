package playlastik.method

sealed trait Method

  case object Get extends Method
  case object Put extends Method
  case object Post extends Method
  case object Delete extends Method
