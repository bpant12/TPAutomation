package com.teachingpark.uiautomation.reports;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.testng.asserts.IAssert;
import org.testng.asserts.IAssertLifecycle;
import org.testng.collections.Maps;

import com.relevantcodes.extentreports.ExtentTest;
 /**
  * 
  * Soft assert Class
  * SFSoftAssert
  * @author bpant12
  */
public abstract class TPSoftAssert implements IAssertLifecycle {
 
	TPReporter report;
	public TPSoftAssert(TPReporter report){
			this.report = report;
	}

	protected final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
	 
	protected void doAssert(IAssert<?> assertCommand,ExtentTest test) {
    
  }
  
  public void assertAll() {
	    if (!m_errors.isEmpty()) {
	      StringBuilder sb = new StringBuilder("The following asserts failed:");
	      boolean first = true;
	      for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
	        if (first) {
	          first = false;
	        } else {
	          sb.append(",");
	        }
	        sb.append("\n\t");
	        sb.append(ae.getKey().getMessage());
	      }
	      m_errors.clear();
	      throw new AssertionError(sb.toString());
	    }
	  }

  /**
   * Run the assert command in parameter. Meant to be overridden by subclasses.
   */
  @Override
  public void executeAssert(IAssert<?> assertCommand) {
    assertCommand.doAssert();
  }

  /**
   * Invoked when an assert succeeds. Meant to be overridden by subclasses.
   */
  @Override
  public void onAssertSuccess(IAssert<?> assertCommand) {
	  
  }

  /**
   * Invoked when an assert fails. Meant to be overridden by subclasses.
   * 
   * @deprecated use onAssertFailure(IAssert assertCommand, AssertionError ex) instead of.
   */
  @Deprecated
  @Override
  public void onAssertFailure(IAssert<?> assertCommand) {
  }
  
  @Override
  public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
      onAssertFailure(assertCommand);
  }

  /**
   * Invoked before an assert is run. Meant to be overridden by subclasses.
   */
  @Override
  public void onBeforeAssert(IAssert<?> assertCommand) {
  }

  /**
   * Invoked after an assert is run. Meant to be overridden by subclasses.
   */
  @Override
  public void onAfterAssert(IAssert<?> assertCommand) {
  }

  abstract private static class SimpleAssert<T> implements IAssert<T> {
    private final T actual;
    private final T expected;
    private final String m_message;

    public SimpleAssert(String message) {
      this(null, null, message);
    }

    public SimpleAssert(T actual, T expected) {
      this(actual, expected, null);
    }

    public SimpleAssert(T actual, T expected, String message) {
      this.actual = actual;
      this.expected = expected;
      m_message = message;
    }

    @Override
    public String getMessage() {
      return m_message;
    }

    @Override
    public T getActual() {
        return actual;
    }

    @Override
    public T getExpected() {
        return expected;
    }

    @Override
    abstract public void doAssert();
  }


  public void assertTrue(final boolean condition, final String message,ExtentTest test) {
	  
    doAssert(new SimpleAssert<Boolean>(condition, Boolean.TRUE, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertTrue(condition, message);
      }
    },test);
  }
  
	public void assertTrue(final boolean condition,ExtentTest test) {
		doAssert(new SimpleAssert<Boolean>(condition, Boolean.TRUE) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertTrue(condition);
			}
		},test);
	}

  public void assertFalse(final boolean condition, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Boolean>(condition, Boolean.FALSE, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertFalse(condition, message);
      }
    },test);
  }

  public void assertFalse(final boolean condition,ExtentTest test) {
    doAssert(new SimpleAssert<Boolean>(condition, Boolean.FALSE) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertFalse(condition);
      }
    },test);
  }

  public void fail(final String message, final Throwable realCause,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(message) {
      @Override
      public void doAssert() {
        org.testng.Assert.fail(message, realCause);
      }
    },test);
  }

  public void fail(final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(message) {
      @Override
      public void doAssert() {
        org.testng.Assert.fail(message);
      }
    },test);
  }

  public void fail(ExtentTest test) {
    doAssert(new SimpleAssert<Object>(null) {
      @Override
      public void doAssert() {
        org.testng.Assert.fail();
      }
    },test);
  }

  public <T> void assertEquals(final T actual, final T expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<T>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }

  public <T> void assertEquals(final T actual, final T expected,ExtentTest test) {
    doAssert(new SimpleAssert<T>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertEquals(final String actual, final String expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<String>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }
  public void assertEquals(final String actual, final String expected,ExtentTest test) {
    doAssert(new SimpleAssert<String>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertEquals(final double actual, final double expected, final double delta,
      final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Double>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, delta, message);
      }
    },test);
  }

  public void assertEquals(final double actual, final double expected, final double delta,ExtentTest test) {
    doAssert(new SimpleAssert<Double>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, delta);
      }
    },test);
  }

  public void assertEquals(final float actual, final float expected, final float delta,
      final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Float>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, delta, message);
      }
    },test);
  }

  public void assertEquals(final float actual, final float expected, final float delta,ExtentTest test) {
    doAssert(new SimpleAssert<Float>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, delta);
      }
    },test);
  }

  public void assertEquals(final long actual, final long expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Long>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }

  public void assertEquals(final long actual, final long expected,ExtentTest test) {
    doAssert(new SimpleAssert<Long>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertEquals(final boolean actual, final boolean expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Boolean>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }

  public void assertEquals(final boolean actual, final boolean expected,ExtentTest test) {
    doAssert(new SimpleAssert<Boolean>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertEquals(final byte actual, final byte expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Byte>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }

  public void assertEquals(final byte actual, final byte expected,ExtentTest test) {
    doAssert(new SimpleAssert<Byte>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertEquals(final char actual, final char expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Character>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }

  public void assertEquals(final char actual, final char expected,ExtentTest test) {
    doAssert(new SimpleAssert<Character>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertEquals(final short actual, final short expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Short>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }

  public void assertEquals(final short actual, final short expected,ExtentTest test) {
    doAssert(new SimpleAssert<Short>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertEquals(final int actual, final  int expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Integer>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }

  public void assertEquals(final int actual, final int expected,ExtentTest test) {
    doAssert(new SimpleAssert<Integer>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertNotNull(final Object object,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(object, null) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotNull(object);
      }
    },test);
  }

  public void assertNotNull(final Object object, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(object, null, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotNull(object, message);
      }
    },test);
  }

  public void assertNull(final Object object,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(object, null) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNull(object);
      }
    },test);
  }

  public void assertNull(final Object object, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(object, null, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNull(object, message);
      }
    },test);
  }

  public void assertSame(final Object actual, final Object expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertSame(actual, expected, message);
      }
    },test);
  }

  public void assertSame(final Object actual, final Object expected,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertSame(actual, expected);
      }
    },test);
  }

  public void assertNotSame(final Object actual, final Object expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotSame(actual, expected, message);
      }
    },test);
  }

  public void assertNotSame(final Object actual, final Object expected,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotSame(actual, expected);
      }
    },test);
  }

  public void assertEquals(final Collection<?> actual, final Collection<?> expected,ExtentTest test) {
    doAssert(new SimpleAssert<Collection<?>>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertEquals(final Collection<?> actual, final Collection<?> expected,
      final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Collection<?>>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }

  public void assertEquals(final Object[] actual, final Object[] expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Object[]>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }

  public void assertEqualsNoOrder(final Object[] actual, final Object[] expected,
      final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Object[]>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEqualsNoOrder(actual, expected, message);
      }
    },test);
  }

  public void assertEquals(final Object[] actual, final Object[] expected,ExtentTest test) {
    doAssert(new SimpleAssert<Object[]>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertEqualsNoOrder(final Object[] actual, final Object[] expected,ExtentTest test) {
    doAssert(new SimpleAssert<Object[]>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEqualsNoOrder(actual, expected);
      }
    },test);
  }

  public void assertEquals(final byte[] actual, final byte[] expected,ExtentTest test) {
    doAssert(new SimpleAssert<byte[]>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertEquals(final byte[] actual, final byte[] expected,
      final String message,ExtentTest test) {
    doAssert(new SimpleAssert<byte[]>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }

  public void assertEquals(final Set<?> actual, final Set<?> expected,ExtentTest test) {
    doAssert(new SimpleAssert<Set<?>>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public void assertEquals(final Set<?> actual, final Set<?> expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Set<?>>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected, message);
      }
    },test);
  }

  public void assertEquals(final Map<?, ?> actual, final Map<?, ?> expected,ExtentTest test) {
    doAssert(new SimpleAssert<Map<?, ?>>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertEquals(actual, expected);
      }
    },test);
  }

  public  void assertNotEquals(final Object actual, final Object expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, message);
      }
    },test);
  }

  public void assertNotEquals(final Object actual, final Object expected,ExtentTest test) {
    doAssert(new SimpleAssert<Object>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected);
      }
    },test);
  }

  void assertNotEquals(final String actual, final String expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<String>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, message);
      }
    },test);
  }

  void assertNotEquals(final String actual, final String expected,ExtentTest test) {
    doAssert(new SimpleAssert<String>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected);
      }
    },test);
  }

  void assertNotEquals(final long actual, final long expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Long>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, message);
      }
    },test);
  }

  void assertNotEquals(final long actual, final long expected,ExtentTest test) {
    doAssert(new SimpleAssert<Long>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected);
      }
    },test);
  }

  void assertNotEquals(final boolean actual, final boolean expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Boolean>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, message);
      }
    },test);
  }

  void assertNotEquals(final boolean actual, final boolean expected,ExtentTest test) {
    doAssert(new SimpleAssert<Boolean>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected);
      }
    },test);
  }

  void assertNotEquals(final byte actual, final byte expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Byte>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, message);
      }
    },test);
  }

  void assertNotEquals(final byte actual, final byte expected,ExtentTest test) {
    doAssert(new SimpleAssert<Byte>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected);
      }
    },test);
  }

  void assertNotEquals(final char actual, final char expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Character>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, message);
      }
    },test);
  }

  void assertNotEquals(final char actual, final char expected,ExtentTest test) {
    doAssert(new SimpleAssert<Character>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected);
      }
    },test);
  }

  void assertNotEquals(final short actual, final short expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Short>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, message);
      }
    },test);
  }

  void assertNotEquals(final short actual, final short expected,ExtentTest test) {
    doAssert(new SimpleAssert<Short>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected);
      }
    },test);
  }

  void assertNotEquals(final int actual, final int expected, final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Integer>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, message);
      }
    },test);
  }

  void assertNotEquals(final int actual, final int expected,ExtentTest test) {
    doAssert(new SimpleAssert<Integer>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected);
      }
    },test);
  }

  public void assertNotEquals(final float actual, final float expected, final float delta,
      final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Float>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, delta, message);
      }
   },test);
  }

  public void assertNotEquals(final float actual, final float expected, final float delta,ExtentTest test) {
    doAssert(new SimpleAssert<Float>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, delta);
      }
    },test);
  }

  public void assertNotEquals(final double actual, final double expected, final double delta,
      final String message,ExtentTest test) {
    doAssert(new SimpleAssert<Double>(actual, expected, message) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, delta, message);
      }
    },test);
  }

  public void assertNotEquals(final double actual, final double expected, final double delta,ExtentTest test) {
    doAssert(new SimpleAssert<Double>(actual, expected) {
      @Override
      public void doAssert() {
        org.testng.Assert.assertNotEquals(actual, expected, delta);
      }
    },test);
  }

}
