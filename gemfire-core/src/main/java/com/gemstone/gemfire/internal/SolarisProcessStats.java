/*=========================================================================
 * Copyright (c) 2002-2014 Pivotal Software, Inc. All Rights Reserved.
 * This product is protected by U.S. and international copyright
 * and intellectual property laws. Pivotal products are covered by
 * more patents listed at http://www.pivotal.io/patents.
 *========================================================================
 */

package com.gemstone.gemfire.internal;

import com.gemstone.gemfire.*;

/**
 * <P>This class provides the interface for statistics about a
 * Solaris operating system process that is using a GemFire system.
 */
public class SolarisProcessStats
{
  private final static int allOtherSleepTimeINT = 0;
  private final static int characterIoINT = 1;
  private final static int dataFaultSleepTimeINT = 2;
  private final static int heapSizeINT = 3;
  private final static int imageSizeINT = 4;
  private final static int involContextSwitchesINT = 5;
  private final static int kernelFaultSleepTimeINT = 6;
  private final static int lockWaitSleepTimeINT = 7;
  private final static int lwpCurCountINT = 8;
  private final static int lwpTotalCountINT = 9;
  private final static int majorFaultsINT = 10;
  private final static int messagesRecvINT = 11;
  private final static int messagesSentINT = 12;
  private final static int minorFaultsINT = 13;
  private final static int rssSizeINT = 14;
  private final static int signalsReceivedINT = 15;
  private final static int systemCallsINT = 16;
  private final static int stackSizeINT = 17;
  private final static int stoppedTimeINT = 18;
  private final static int systemTimeINT = 19;
  private final static int textFaultSleepTimeINT = 20;
  private final static int trapTimeINT = 21;
  private final static int userTimeINT = 22;
  private final static int volContextSwitchesINT = 23;
  private final static int waitCpuTimeINT = 24;

  private final static int activeTimeLONG = 0;

  private final static int cpuUsedDOUBLE = 0;
  private final static int memoryUsedDOUBLE = 1;

  private final static StatisticsType myType;

  private static void checkOffset(String name, int offset) {
    int id = myType.nameToId(name);
    Assert.assertTrue(offset == id, "Expected the offset for " + name + " to be " + offset + " but it was " + id);
  }

  static {
    StatisticsTypeFactory f = StatisticsTypeFactoryImpl.singleton();
    myType = f.createType("SolarisProcessStats",
                          "Statistics on a Solaris process.",
                          new StatisticDescriptor[] {
                            f.createIntCounter("allOtherSleepTime",
                                                "The number of milliseconds the process has been sleeping for some reason not tracked by any other stat. Note that all lwp's contribute to this stat's value so check lwpCurCount to understand large values.",
                                                "milliseconds", false),
                            f.createIntCounter("characterIo",
                                                "The number of characters read and written.",
                                                "bytes"),
                            f.createIntCounter("dataFaultSleepTime",
                                                "The number of milliseconds the process has been faulting in data pages.",
                                                "milliseconds", false),
                            f.createIntGauge("heapSize",
                                                "The size of the process's heap in megabytes.",
                                                "megabytes"),
                            f.createIntGauge("imageSize",
                                                "The size of the process's image in megabytes.",
                                                "megabytes"),
                            f.createIntCounter("involContextSwitches",
                                                "The number of times the process was forced to do a context switch.",
                                                "operations", false),
                            f.createIntCounter("kernelFaultSleepTime",
                                                "The number of milliseconds the process has been faulting in kernel pages.",
                                                "milliseconds", false),
                            f.createIntCounter("lockWaitSleepTime",
                                                "The number of milliseconds the process has been waiting for a user lock. Note that all lwp's contribute to this stat's value so check lwpCurCount to understand large values.",
                                                "milliseconds", false),
                            f.createIntGauge("lwpCurCount",
                                                "The current number of light weight processes that exist in the process.",
                                                "threads"),
                            f.createIntCounter("lwpTotalCount",
                                                "The total number of light weight processes that have ever contributed to the process's statistics.",
                                                "threads", false),
                            f.createIntCounter("majorFaults",
                                                "The number of times the process has had a page fault that needed disk access.",
                                                "operations", false),
                            f.createIntCounter("messagesRecv",
                                                "The number of messages received by the process.",
                                                "messages"),
                            f.createIntCounter("messagesSent",
                                                "The number of messages sent by the process.",
                                                "messages"),
                            f.createIntCounter("minorFaults",
                                                "The number of times the process has had a page fault that did not need disk access.",
                                                "operations", false),
                            f.createIntGauge("rssSize",
                                                "The size of the process's resident set size in megabytes.",
                                                "megabytes"),
                            f.createIntCounter("signalsReceived",
                                                "The total number of operating system signals this process has received.",
                                                "signals", false),
                            f.createIntCounter("systemCalls",
                                                "The total number system calls done by this process.",
                                                "operations"),
                            f.createIntGauge("stackSize",
                                                "The size of the process's stack in megabytes.",
                                                "megabytes"),
                            f.createIntCounter("stoppedTime",
                                                "The number of milliseconds the process has been stopped.",
                                                "milliseconds", false),
                            f.createIntCounter("systemTime",
                                                "The number of milliseconds the process has been using the CPU to execute system calls.",
                                                "milliseconds", false),
                            f.createIntCounter("textFaultSleepTime",
                                                "The number of milliseconds the process has been faulting in text pages.",
                                                "milliseconds", false),
                            f.createIntCounter("trapTime",
                                                "The number of milliseconds the process has been in system traps.",
                                                "milliseconds", false),
                            f.createIntCounter("userTime",
                                                "The number of milliseconds the process has been using the CPU to execute user code.",
                                                "milliseconds", false),
                            f.createIntCounter("volContextSwitches",
                                                "The number of voluntary context switches done by the process.",
                                                "operations", false),
                            f.createIntCounter("waitCpuTime",
                                                "The number of milliseconds the process has been waiting for a CPU due to latency.",
                                                "milliseconds", false),


                            f.createLongCounter("activeTime",
                                                "The number of milliseconds the process has been using the CPU to execute user or system code.",
                                                "milliseconds", false),


                            f.createDoubleGauge("cpuUsed",
                                                "The percentage of recent cpu time used by the process.",
                                                "%"),
                            f.createDoubleGauge("memoryUsed",
                                                "The percentage of real memory used by the process.",
                                                "%")
                          });

    checkOffset("allOtherSleepTime", allOtherSleepTimeINT);
    checkOffset("characterIo", characterIoINT);
    checkOffset("dataFaultSleepTime", dataFaultSleepTimeINT);
    checkOffset("heapSize", heapSizeINT);
    checkOffset("imageSize", imageSizeINT);
    checkOffset("involContextSwitches", involContextSwitchesINT);
    checkOffset("kernelFaultSleepTime", kernelFaultSleepTimeINT);
    checkOffset("lockWaitSleepTime", lockWaitSleepTimeINT);
    checkOffset("lwpCurCount", lwpCurCountINT);
    checkOffset("lwpTotalCount", lwpTotalCountINT);
    checkOffset("majorFaults", majorFaultsINT);
    checkOffset("messagesRecv", messagesRecvINT);
    checkOffset("messagesSent", messagesSentINT);
    checkOffset("minorFaults", minorFaultsINT);
    checkOffset("rssSize", rssSizeINT);
    checkOffset("signalsReceived", signalsReceivedINT);
    checkOffset("systemCalls", systemCallsINT);
    checkOffset("stackSize", stackSizeINT);
    checkOffset("stoppedTime", stoppedTimeINT);
    checkOffset("systemTime", systemTimeINT);
    checkOffset("textFaultSleepTime", textFaultSleepTimeINT);
    checkOffset("trapTime", trapTimeINT);
    checkOffset("userTime", userTimeINT);
    checkOffset("volContextSwitches", volContextSwitchesINT);
    checkOffset("waitCpuTime", waitCpuTimeINT);

    checkOffset("activeTime", activeTimeLONG);

    checkOffset("cpuUsed", cpuUsedDOUBLE);
    checkOffset("memoryUsed", memoryUsedDOUBLE);
  }

  private SolarisProcessStats() {
    // no instances allowed
  }
  public static StatisticsType getType() {
    return myType;
  }

  /**
   * Returns a <code>ProcessStats</code> that wraps Solaris process
   * <code>Statistics</code>. 
   *
   * @since 3.5
   */
  static ProcessStats createProcessStats(final Statistics stats) {
    if (stats instanceof LocalStatisticsImpl) {
      HostStatHelper.refresh((LocalStatisticsImpl) stats);
    } // otherwise its a Dummy implementation so do nothing
    return new ProcessStats(stats) {
      @Override
        public long getProcessSize() {
          return stats.getInt(rssSizeINT);
        }
      };
  }
}