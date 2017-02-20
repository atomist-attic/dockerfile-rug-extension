package com.atomist.rug.kind.dockerfile

import org.scalatest.{FlatSpec, Matchers}

class DockerfileTest extends FlatSpec with Matchers {

  it should "get it" in {
    assert(Dockerfile().it === "it")
  }
}
