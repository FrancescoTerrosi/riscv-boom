package boom.exu

import chisel3._
import chisel3.util._

import freechips.rocketchip.config.Parameters
import freechips.rocketchip.rocket.CSR
import freechips.rocketchip.rocket
import freechips.rocketchip.tilelink._
import testchipip.{ExtendedTracedInstruction}

import freechips.rocketchip.util.CoreMonitorBundle

import boom.common._
import boom.util._



class Monitorino(implicit p: Parameters) extends BoomModule()(p)
{

  val io = IO(new Bundle {
    val interrupts = Flipped(new freechips.rocketchip.tile.CoreInterrupts())
    val trace = Input(Vec(coreParams.retireWidth, new ExtendedTracedInstruction))
  })

  val x = RegInit(0.U(64.W))

  val trigger = RegInit(false.B)
  val started = RegInit(false.B)


  when (io.interrupts.msip) {
    started := true.B
  }

  when (started) {
    x := x + 1.U
  }


    printf(p"io = $io\n")
    printf(p"x = $x\n")

}
